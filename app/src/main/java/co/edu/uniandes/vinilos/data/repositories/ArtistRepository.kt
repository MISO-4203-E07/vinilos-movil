package co.edu.uniandes.vinilos.data.repositories

import co.edu.uniandes.vinilos.data.net.RetrofitBroker

class ArtistRepository () {

    //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
    suspend fun getAllArtists() = RetrofitBroker.getAllArtists()

    suspend fun getArtistById(id: Int) = RetrofitBroker.getArtistById(id)

}