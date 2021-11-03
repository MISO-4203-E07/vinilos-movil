package co.edu.uniandes.vinilos.data.net

import co.edu.uniandes.vinilos.data.model.Album
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApi {

    @GET("albums")
    fun getAllAlbums() : Call<List<Album>>

    @GET("albums/{id}")
    fun getAlbumById(@Path("id") id: String) : Call<Album>

}