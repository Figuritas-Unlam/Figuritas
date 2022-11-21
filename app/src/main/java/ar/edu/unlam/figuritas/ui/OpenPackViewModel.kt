package ar.edu.unlam.figuritas.ui

import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.figuritas.data.repository.DatabaseRepository
import ar.edu.unlam.figuritas.data.repository.PlayerRepository
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import ar.edu.unlam.figuritas.model.response.mapToEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpenPackViewModel @Inject constructor(
    private val repository: PlayerRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {
    private val _playersData = MutableLiveData<List<PlayerResponse?>>()
    val playersData: LiveData<List<PlayerResponse?>> = _playersData
    var playerRepetidos: MutableList<PlayerEntity> = mutableListOf()
    var playerNuevas: MutableList<PlayerEntity> = mutableListOf()

    init {
        fetchPlayers()
    }

    private fun fetchPlayers() {
        try {
            viewModelScope.launch {
                val response = repository.getRandomPlayers(5)
                _playersData.value = response
                for(player in response){
                    player!!.data.imageCountry = repository.getCountryById(player.data.countryId)!!.data.image
                    databaseRepository.insertPlayer(player)
                }
            }
        } catch (e: RuntimeException) {
            e.printStackTrace()
            Log.e("Error fetching players", e.message.toString())
        }
    }

    fun setLists(){
        getRepeats()
        getNews()
    }

    private fun getNews() {
        playerNuevas.addAll(databaseRepository.getPlayersNotPaste())
    }

    private fun getRepeats() {
        playerRepetidos.addAll(databaseRepository.getRepeats())
    }
}

