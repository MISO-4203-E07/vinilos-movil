package co.edu.uniandes.vinilos.view.collector

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.databinding.ActivityCollectorDetailBinding
import co.edu.uniandes.vinilos.view.album.AlbumAdapter
import co.edu.uniandes.vinilos.view.album.DetailAlbumActivity
import co.edu.uniandes.vinilos.viewmodel.CollectorViewModel
import org.jetbrains.anko.toast

class CollectorDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCollectorDetailBinding
    private val collectorViewModel: CollectorViewModel by viewModels()
    private var idCollector: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_collector_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val adapter = AlbumAdapter()
        binding.rvCollectorAlbums.adapter = adapter
        adapter.onAlbumSelected = this::goToDetailAlbum

        idCollector = intent.getIntExtra("ID_COLLECTOR", 0)

        collectorViewModel.collector.observe(this, { collect ->
            if (collect == null)
                toast(R.string.activity_collector_detail_collector_not_exists)
            else {
                binding.collector = collect
                supportActionBar?.title = "Collector - ${collect.name}"
                collectorViewModel.getAlbumesByIdCollector(collect.id)
            }
        })

        collectorViewModel.albumesCollector.observe(this, { list ->
            val albumes: MutableList<Album> = mutableListOf()
            list.forEach { collectorAlbum -> albumes.add(collectorAlbum.album) }
            if (albumes.isNotEmpty())
                adapter.data = albumes
            else {
                binding.tvLabelNoAlbums.visibility = View.VISIBLE
            }
        })

        collectorViewModel.getCollectorsById(idCollector)

    }

    private fun goToDetailAlbum(id: Int) {
        val intent = Intent(binding.root.context, DetailAlbumActivity::class.java).apply {
            putExtra("ID_ALBUM", id)
        }
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}