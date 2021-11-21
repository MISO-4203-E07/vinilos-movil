package co.edu.uniandes.vinilos.data.net

import android.content.Context
import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.data.model.Performer

class CacheManager(context: Context) {

    companion object {
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }

    private var performers: HashMap<Int, Performer> = hashMapOf()
    private var albums: HashMap<Int, Album> = hashMapOf()

    fun addPerformer(performerId: Int, performer: Performer?) {
        performer?.let { artist ->
            if (!performers.containsKey(performerId))
                performers[performerId] = artist
        }
    }

    fun getPerformer(performerId: Int): Performer? {
        return if (performers.containsKey(performerId)) performers[performerId]!! else null
    }

    fun addAlbum(albumId: Int, album: Album?) {
        album?.let { alb ->
            if (!albums.containsKey(albumId))
                albums[albumId] = alb
        }
    }

    fun getAlbum(albumId: Int): Album? {
        return if (albums.containsKey(albumId)) albums[albumId] else null
    }

}