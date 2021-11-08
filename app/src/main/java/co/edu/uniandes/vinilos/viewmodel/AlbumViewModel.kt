package co.edu.uniandes.vinilos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.data.repositories.AlbumRepository

class AlbumViewModel : ViewModel() {

    val listAlbums = MutableLiveData<List<Album>>()
    val album = MutableLiveData<Album>()
    private val albumsRepository = AlbumRepository()

    fun getAlbums() {
        albumsRepository.getAllAlbums(onResponse = {
            listAlbums.postValue(it)
        })
    }

    fun getAlbumById(id: Int) {
        albumsRepository.getAlbumById(id, onResponse = {
            album.postValue(it)
        })
    }

}