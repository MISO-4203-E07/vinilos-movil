package co.edu.uniandes.vinilos.data.net

import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.data.model.Performer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitBroker {

    companion object {

        fun getAllAlbums (onResponse : (resp:List<Album>) -> Unit, onFailure : (err: String) -> Unit) {
            val request = ApiClient.albums.getAllAlbums()
            request.enqueue(object : Callback<List<Album>> {
                override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                    onResponse(response.body() ?: listOf())
                }

                override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                    onFailure(t.message ?: "Failure API")
                }

            })
        }

        fun getAlbumById (id: Int, onResponse : (resp:Album?) -> Unit, onFailure : (err: String) -> Unit) {
            val request = ApiClient.albums.getAlbumById(id.toString())
            request.enqueue(object : Callback<Album> {
                override fun onResponse(call: Call<Album>, response: Response<Album>) {
                    onResponse(response.body())
                }

                override fun onFailure(call: Call<Album>, t: Throwable) {
                    onFailure(t.message ?: "Failure API")
                }

            })
        }
        fun getAllArtists (onResponse : (resp:List<Performer>) -> Unit, onFailure : (err: String) -> Unit) {
            val request = ApiClient.artist.getAllArtists()
            request.enqueue(object : Callback<List<Performer>> {
                override fun onResponse(call: Call<List<Performer>>, response: Response<List<Performer>>) {
                    onResponse(response.body() ?: listOf())
                }

                override fun onFailure(call: Call<List<Performer>>, t: Throwable) {
                    onFailure(t.message ?: "Failure API")
                }

            })
        }

        fun getArtistById (id: Int, onResponse : (resp:Performer?) -> Unit, onFailure : (err: String) -> Unit) {
            val request = ApiClient.artist.getArtistById(id.toString())
            request.enqueue(object : Callback<Performer> {
                override fun onResponse(call: Call<Performer>, response: Response<Performer>) {
                    onResponse(response.body())
                }

                override fun onFailure(call: Call<Performer>, t: Throwable) {
                    onFailure(t.message ?: "Failure API")
                }

            })
        }
    }

}