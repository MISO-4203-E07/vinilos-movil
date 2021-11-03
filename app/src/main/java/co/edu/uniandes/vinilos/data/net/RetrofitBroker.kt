package co.edu.uniandes.vinilos.data.net

import co.edu.uniandes.vinilos.data.model.Album
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitBroker {

    companion object {

        fun getAllAlbums (onResponse : (resp:List<Album>) -> Unit, onFailure : (err: String) -> Unit) {
            val request = ApiClient.albums.getAllAlbums()
            val response = request.enqueue(object : Callback<List<Album>> {
                override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                    onResponse(response.body() ?: listOf())
                }

                override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                    onFailure(t.message ?: "Error en el llamado al servicio")
                }

            })
        }

    }

}