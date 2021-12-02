package co.edu.uniandes.vinilos.data.repositories

import android.app.Application
import android.util.Log
import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.data.net.CacheManager
import co.edu.uniandes.vinilos.data.net.RetrofitBroker

class AlbumRepository (val application: Application) {

    //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
    suspend fun getAllAlbums(): List<Album> = RetrofitBroker.getAllAlbums()

    suspend fun getAlbumById(id: Int): Album? {
        val potentialResp = CacheManager.getInstance(application.applicationContext).getAlbum(id)
        return if (potentialResp == null) {
            Log.d("Cache decision", "get from network")
            val album = RetrofitBroker.getAlbumById(id)
            CacheManager.getInstance(application.applicationContext).addAlbum(id, album)
            album
        } else {
            Log.d("Cache decision", "return Album with id ${potentialResp.id} from cache")
            potentialResp
        }
    }

    suspend fun createAlbum(album: Album) = RetrofitBroker.insertAlbum(album)

}