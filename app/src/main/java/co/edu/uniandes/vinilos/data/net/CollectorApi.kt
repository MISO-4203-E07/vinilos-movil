package co.edu.uniandes.vinilos.data.net

import co.edu.uniandes.vinilos.data.model.Collector
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectorApi {

    @GET("collectors")
    fun getAllCollectors() : Call<List<Collector>>

    @GET("collectors/{id}")
    fun getCollectorById(@Path("id") id: String) : Call<Collector>

}