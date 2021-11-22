package co.edu.uniandes.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinilos.data.model.Performer
import co.edu.uniandes.vinilos.data.repositories.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistViewModel(application: Application) : AndroidViewModel(application) {

    val listArtist = MutableLiveData<List<Performer>>()
    val performer = MutableLiveData<Performer>()
    private val artistRepository = ArtistRepository(application)

    fun getArtists() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                listArtist.postValue(artistRepository.getAllArtists())
            }
        } catch (e: Exception) {
            Log.e("Error", e.message ?: "Failure service")
        }
    }

    fun getArtistById(id: Int) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                performer.postValue(artistRepository.getArtistById(id))
            }
        } catch (e: Exception) {
            Log.e("Error", e.message ?: "Failure service")
        }
    }
}