package co.edu.uniandes.vinilos.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinilos.data.model.Collector
import co.edu.uniandes.vinilos.data.repositories.CollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CollectorViewModel : ViewModel() {
    val listCollector = MutableLiveData<List<Collector>>()
    val collector = MutableLiveData<Collector>()
    private val collectorRepository = CollectorRepository()

    fun getCollectors() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                listCollector.postValue(collectorRepository.getAllCollectors())
            }
        } catch (e: Exception) {
            Log.e("Error", e.message ?: "Failure service")
        }
    }

    fun getCollectorsById(id: Int) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                collector.postValue(collectorRepository.getCollectorById(id))
            }
        } catch (e: Exception) {
            Log.e("Error", e.message ?: "Failure service")
        }
    }
}

