package co.edu.uniandes.vinilos.view.album

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.databinding.ActivityDetailAlbumBinding
import co.edu.uniandes.vinilos.viewmodel.AlbumViewModel
import org.jetbrains.anko.toast

class DetailAlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAlbumBinding
    private val albumViewModel: AlbumViewModel by viewModels()
    var idAlbum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_album)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        idAlbum = intent.getIntExtra("ID_ALBUM", 0)
        albumViewModel.album.observe(this, Observer { album ->
            binding.album = album
            supportActionBar?.title = "Vinilos - ${album.name}"
            if (album == null)
                toast(R.string.activity_detail_album_album_not_exists)
        })

        albumViewModel.getAlbumById(idAlbum)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}