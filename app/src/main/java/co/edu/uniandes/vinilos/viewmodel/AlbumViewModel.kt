package co.edu.uniandes.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.data.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumViewModel(application: Application) : AndroidViewModel(application) {

    val listAlbums = MutableLiveData<List<Album>>()
    val album = MutableLiveData<Album>()
    private val albumsRepository = AlbumRepository(application)

    fun getAlbums() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                listAlbums.postValue(albumsRepository.getAllAlbums())
            }
        } catch (e: Exception) {
            Log.e("Error", e.message ?: "Failure service")
        }
    }

    fun getAlbumById(id: Int) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                album.postValue(albumsRepository.getAlbumById(id))
            }
        } catch (e: Exception) {
            Log.e("Error", e.message ?: "Failure service")
        }
    }

}