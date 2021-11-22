package co.edu.uniandes.vinilos.data.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import co.edu.uniandes.vinilos.data.model.Collector
import co.edu.uniandes.vinilos.data.net.CacheManager
import co.edu.uniandes.vinilos.data.net.RetrofitBroker
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONArray

class CollectorRepository (val application: Application) {
    val format = Json

    //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
    suspend fun getAllCollectors() = RetrofitBroker.getAllCollectors()

    suspend fun getCollectorById(id: Int): Collector? {
        var collector = getCollector(id)
        return if (collector == null) {
            val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if ( cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE) {
                null
            } else {
                collector = RetrofitBroker.getCollectorById(id)
                addCollector(id, collector)
                collector
            }
        } else collector
    }

    suspend fun getCollector(collectorId: Int): Collector? {
        val prefs = CacheManager.getPrefs(application.baseContext, CacheManager.COLLECTORS_SPREFS)
        if (prefs.contains(collectorId.toString())) {
            val storedVal = prefs.getString(collectorId.toString(), "")
            if (!storedVal.isNullOrBlank()) {
                val resp = JSONArray(storedVal)
                Log.d("deserialize", resp.toString())
                return format.decodeFromString<Collector>(storedVal)
            }
        }
        return null
    }

    suspend fun addCollector(collectorId: Int, collector: Collector?){
        val prefs = CacheManager.getPrefs(application.baseContext, CacheManager.COLLECTORS_SPREFS)
        if (!prefs.contains(collectorId.toString())) {
            val store = format.encodeToString(collector)
            with(prefs.edit(),{
                putString(collectorId.toString(), store)
                apply()
            })
        }
    }

}