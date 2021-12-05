package co.edu.uniandes.vinilos.data.repositories

import android.app.Application
import android.util.Log
import co.edu.uniandes.vinilos.data.model.Collector
import co.edu.uniandes.vinilos.data.net.CacheManager
import co.edu.uniandes.vinilos.data.net.RetrofitBroker

class CollectorRepository (val application: Application) {

    //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
    suspend fun getAllCollectors() = RetrofitBroker.getAllCollectors()

    suspend fun getAlbumesByCollector(id: Int) = RetrofitBroker.getAlbumesByIdCollector(id)

    suspend fun getCollectorById(id: Int): Collector? {
        val potentialResp = CacheManager.getInstance(application.applicationContext).getCollector(id)
        return if (potentialResp == null) {
            Log.d("Cache decision", "get from network")
            val collector = RetrofitBroker.getCollectorById(id)
            CacheManager.getInstance(application.applicationContext).addCollector(id, collector)
            collector
        } else {
            Log.d("Cache decision", "return Album with id ${potentialResp.id} from cache")
            potentialResp
        }
    }

}