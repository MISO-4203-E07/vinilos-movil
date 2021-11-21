package co.edu.uniandes.vinilos.data.repositories

import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.data.net.RetrofitBroker

class AlbumRepository () {

    //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
    suspend fun getAllAlbums(): List<Album> = RetrofitBroker.getAllAlbums()

    suspend fun getAlbumById(id: Int) = RetrofitBroker.getAlbumById(id)

}