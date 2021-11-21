package co.edu.uniandes.vinilos.data.net

import co.edu.uniandes.vinilos.data.model.Collector
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectorApi {

    @GET("collectors")
    suspend fun getAllCollectors() : Response<List<Collector>>

    @GET("collectors/{id}")
    suspend fun getCollectorById(@Path("id") id: String) : Response<Collector>

}