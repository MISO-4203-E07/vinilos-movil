package co.edu.uniandes.vinilos.data.net

import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.data.model.Collector
import co.edu.uniandes.vinilos.data.model.Performer

class RetrofitBroker {

    companion object {

        suspend fun getAllAlbums (): List<Album> {
            val request = ApiClient.albums.getAllAlbums()
            return if (request.isSuccessful)
                request.body() ?: listOf()
            else
                listOf()
        }

        suspend fun getAlbumById (id: Int): Album? {
            val request = ApiClient.albums.getAlbumById(id.toString())
            return if (request.isSuccessful)
                request.body()
            else
                null
        }

        suspend fun getAllArtists(): List<Performer> {
            val request = ApiClient.artist.getAllArtists()
            return if (request.isSuccessful)
                request.body() ?: listOf()
            else
                listOf()
        }

        suspend fun getArtistById(id: Int): Performer? {
            val request = ApiClient.artist.getArtistById(id.toString())
            return if (request.isSuccessful)
                request.body()
            else
                null
        }


        suspend fun getAllCollectors(): List<Collector> {
            val request = ApiClient.collector.getAllCollectors()
            return if (request.isSuccessful)
                request.body() ?: listOf()
            else
                listOf()
        }

        suspend fun getCollectorById(id: Int): Collector? {
            val request = ApiClient.collector.getCollectorById(id.toString())
            return if (request.isSuccessful)
                request.body()
            else
                null
        }
    }
}