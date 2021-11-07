package co.edu.uniandes.vinilos.data.repositories

import android.util.Log
import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.data.net.RetrofitBroker

class AlbumRepository () {

    fun getAllAlbums(onResponse: (List<Album>) -> Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        RetrofitBroker.getAllAlbums(onResponse = { albums ->
            onResponse(albums)
        }, onFailure = {
            onResponse(listOf())
        })
    }

    fun getAlbumById(id: Int, onResponse: (Album?) -> Unit) {
        RetrofitBroker.getAlbumById(id, onResponse = { album ->
            onResponse(album)
        }, onFailure = { e ->
            Log.e("Error", e)
        })
    }

}