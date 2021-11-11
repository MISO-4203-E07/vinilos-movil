package co.edu.uniandes.vinilos.data.repositories

import android.util.Log
import co.edu.uniandes.vinilos.data.model.Performer
import co.edu.uniandes.vinilos.data.net.RetrofitBroker

class ArtistRepository () {

    fun getAllArtists(onResponse: (List<Performer>) -> Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        RetrofitBroker.getAllArtists(onResponse = { performers ->
            onResponse(performers)
        }, onFailure = {
            onResponse(listOf())
        })
    }

    fun getArtistById(id: Int, onResponse: (Performer?) -> Unit) {
        RetrofitBroker.getArtistById(id, onResponse = { performer ->
            onResponse(performer)
        }, onFailure = { e ->
            Log.e("Error", e)
        })
    }

}