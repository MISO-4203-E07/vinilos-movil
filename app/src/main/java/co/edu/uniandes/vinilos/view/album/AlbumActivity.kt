package co.edu.uniandes.vinilos.view.album

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import co.edu.uniandes.vinilos.databinding.ActivityAlbumBinding
import co.edu.uniandes.vinilos.viewmodel.AlbumViewModel

class AlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumBinding
    private val albumViewModel: AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = AlbumAdapter()
        binding.rvAlbums.adapter = adapter

        adapter.onAlbumSelected = this::goToDetailAlbum

        albumViewModel.listAlbums.observe(this, Observer {
            adapter.data = it
        })

        albumViewModel.getAlbums()

    }

    private fun goToDetailAlbum(id: Int) {
        val intent = Intent(this, DetailAlbumActivity::class.java).apply {
            putExtra("ID_ALBUM", id)
        }
        startActivity(intent)
    }
}