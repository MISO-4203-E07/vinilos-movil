package co.edu.uniandes.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.data.model.Track
import co.edu.uniandes.vinilos.data.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumViewModel(application: Application) : AndroidViewModel(application) {

    val listAlbums = MutableLiveData<List<Album>>()
    val album = MutableLiveData<Album>()
    val albumCreated = MutableLiveData<Album>()
    val trackAdded = MutableLiveData<Track>()
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

    fun insertAlbum(album: Album) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                albumCreated.postValue(albumsRepository.createAlbum(album))
            }
        } catch (e: java.lang.Exception) {
            Log.e("Error", e.message ?: "Failure service")
        }
    }

    fun addTrackAlbum(id: String, track: Track) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                trackAdded.postValue(albumsRepository.addTrackAlbum(id, track))
            }
        } catch (e: java.lang.Exception) {
            Log.e("Error", e.message ?: "Failure service")
        }
    }

}