package co.edu.uniandes.vinilos.data.repositories

import android.app.Application
import android.util.Log
import co.edu.uniandes.vinilos.data.model.Performer
import co.edu.uniandes.vinilos.data.net.CacheManager
import co.edu.uniandes.vinilos.data.net.RetrofitBroker

class ArtistRepository (val application: Application) {

    //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
    suspend fun getAllArtists() = RetrofitBroker.getAllArtists()

    suspend fun getArtistById(id: Int): Performer? {

        val potentialResp = CacheManager.getInstance(application.applicationContext).getPerformer(id)
        return if (potentialResp == null) {
            Log.d("Cache decision", "get from network")
            val performer = RetrofitBroker.getArtistById(id)
            CacheManager.getInstance(application.applicationContext).addPerformer(id, performer)
            performer
        } else {
            Log.d("Cache decision", "return Performer with id ${potentialResp.id} from cache")
            potentialResp
        }

    }

}