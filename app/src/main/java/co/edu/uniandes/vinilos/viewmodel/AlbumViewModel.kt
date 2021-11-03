package co.edu.uniandes.vinilos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.data.net.RetrofitBroker

class AlbumViewModel : ViewModel() {

    val listAlbums = MutableLiveData<List<Album>>()

    fun getAlbums() {
        RetrofitBroker.getAllAlbums(onResponse = { listaAlbumes ->
            listAlbums.postValue(listaAlbumes)
        }, onFailure = {
            listAlbums.postValue(listOf())
        })
    }

}