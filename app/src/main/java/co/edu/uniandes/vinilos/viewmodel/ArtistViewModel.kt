package co.edu.uniandes.vinilos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.uniandes.vinilos.data.model.Performer
import co.edu.uniandes.vinilos.data.repositories.ArtistRepository

class ArtistViewModel : ViewModel() {

    val listArtist = MutableLiveData<List<Performer>>()
    val album = MutableLiveData<Performer>()
    private val artistRepository = ArtistRepository()

    fun getArtists() {
        artistRepository.getAllArtists(onResponse = {
            listArtist.postValue(it)
        })
    }

    fun getArtistById(id: Int) {
        artistRepository.getArtistById(id, onResponse = {
            album.postValue(it)
        })
    }
}