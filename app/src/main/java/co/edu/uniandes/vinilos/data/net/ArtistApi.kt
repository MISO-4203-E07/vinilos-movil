package co.edu.uniandes.vinilos.data.net

import co.edu.uniandes.vinilos.data.model.Performer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistApi {

    @GET("musicians")
    suspend fun getAllArtists() : Response<List<Performer>>

    @GET("musicians/{id}")
    suspend fun getArtistById(@Path("id") id: String) : Response<Performer>

}