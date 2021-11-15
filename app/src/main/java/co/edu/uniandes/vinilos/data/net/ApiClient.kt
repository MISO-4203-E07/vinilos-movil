package co.edu.uniandes.vinilos.data.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL = "https://apimisoweb.herokuapp.com/"

object ApiClient {

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

    val albums: AlbumApi = retrofit.create(AlbumApi::class.java)

    val artist: ArtistApi = retrofit.create(ArtistApi::class.java)

    val collector: CollectorApi = retrofit.create(CollectorApi::class.java)

}