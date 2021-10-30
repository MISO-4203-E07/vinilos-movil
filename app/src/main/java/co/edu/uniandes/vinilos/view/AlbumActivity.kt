package co.edu.uniandes.vinilos.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.databinding.ActivityAlbumBinding
import com.squareup.picasso.Picasso
import java.util.*

class AlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = AlbumAdapter()

        binding.rvAlbums.adapter = adapter

        val lista: List<Album> = listOf(
            Album(1,
                "Album 1",
                "https://i0.wp.com/hipertextual.com/wp-content/uploads/2016/12/George-Michael-Net-Worth-scaled.jpg",
            Date(),
            "Descripcion 1",
            "Salsa",
            "Sony"),
            Album(2,
                "Album 2",
                "https://i0.wp.com/hipertextual.com/wp-content/uploads/2016/12/George-Michael-Net-Worth-scaled.jpg",
                Date(),
                "Descripcion 2",
                "Salsa",
                "Sony"),
            Album(3,
                "Album 3",
                "https://i0.wp.com/hipertextual.com/wp-content/uploads/2016/12/George-Michael-Net-Worth-scaled.jpg",
                Date(),
                "Descripcion 3",
                "Salsa",
                "Sony")
        )
        adapter.data = lista

    }
}