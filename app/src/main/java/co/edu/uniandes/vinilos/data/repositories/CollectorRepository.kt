package co.edu.uniandes.vinilos.data.repositories

import co.edu.uniandes.vinilos.data.net.RetrofitBroker

class CollectorRepository {

    //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
    suspend fun getAllCollectors() = RetrofitBroker.getAllCollectors()

    suspend fun getCollectorById(id: Int) = RetrofitBroker.getCollectorById(id)

}