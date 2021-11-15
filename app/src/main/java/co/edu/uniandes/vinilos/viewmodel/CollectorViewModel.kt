package co.edu.uniandes.vinilos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.uniandes.vinilos.data.model.Collector
import co.edu.uniandes.vinilos.data.repositories.CollectorRepository

class CollectorViewModel : ViewModel() {
    val listCollector = MutableLiveData<List<Collector>>()
    val album = MutableLiveData<Collector>()
    private val collectorRepository = CollectorRepository()

    fun getCollectors() {
        collectorRepository.getAllCollectors(onResponse = {
            listCollector.postValue(it)
        })
    }

    fun getCollectorsById(id: Int) {
        collectorRepository.getCollectorById(id, onResponse = {
            album.postValue(it)
        })
    }
}

