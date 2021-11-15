package co.edu.uniandes.vinilos.data.repositories

import android.util.Log
import co.edu.uniandes.vinilos.data.model.Collector
import co.edu.uniandes.vinilos.data.net.RetrofitBroker

class CollectorRepository {

    fun getAllCollectors(onResponse: (List<Collector>) -> Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        RetrofitBroker.getAllCollectors(onResponse = { collectors ->
            onResponse(collectors)
        }, onFailure = {
            onResponse(listOf())
        })
    }

    fun getCollectorById(id: Int, onResponse: (Collector?) -> Unit) {
        RetrofitBroker.getCollectorById(id, onResponse = { collector ->
            onResponse(collector)
        }, onFailure = { e ->
            Log.e("Error", e)
        })
    }

}