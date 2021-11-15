package co.edu.uniandes.vinilos.data.net

import co.edu.uniandes.vinilos.data.model.Performer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistApi {

    @GET("musicians")
    fun getAllArtists() : Call<List<Performer>>

    @GET("musicians/{id}")
    fun getArtistById(@Path("id") id: String) : Call<Performer>

}