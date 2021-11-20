package co.edu.uniandes.vinilos.data.model

import androidx.core.os.bundleOf
import co.edu.uniandes.vinilos.view.album.AlbumAdapter
import java.util.*

object AlbumModelTest {

    val album = Album(
        id = 1,
        name = "myname",
        cover =  "mycover",
        releaseDate = Date(),
        description = "",
        genre = "",
        recordLabel = "",
        tracks  = listOf(),
        performers = listOf()
    )


    val testBundle = bundleOf("binding.rvAlbums.adapter.data" to listOf(album))
}