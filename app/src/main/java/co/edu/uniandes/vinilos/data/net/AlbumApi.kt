package co.edu.uniandes.vinilos.data.net

import co.edu.uniandes.vinilos.data.model.Album
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApi {

    @GET("albums")
    suspend fun getAllAlbums() : Response<List<Album>>

    @GET("albums/{id}")
    suspend fun getAlbumById(@Path("id") id: String) : Response<Album>

}