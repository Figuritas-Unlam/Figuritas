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
    var playerRepetidos: MutableList<PlayerEntity?> = mutableListOf()
    var playerNuevas: MutableList<PlayerEntity?> = mutableListOf()
    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    init {
        fetchPlayers()
    }


    fun getFirstPlayerRepets(): PlayerEntity? {
        return databaseRepository.getRepeats()?.first()
    }

    fun getFirstPlayerNueva(): PlayerEntity? {
        return databaseRepository.getPlayersNotPaste().first()
    }

    private fun fetchPlayers() {
        viewModelScope.launch {
            try {
                _error.value = true

                val response = repository.getRandomPlayers(5)
                _playersData.value = response
                for (player in response) {
                    player?.data?.imageCountry = player?.data?.countryId?.let {
                        repository.getCountryById(
                            it
                        )?.data
                    }!!.image
                    databaseRepository.insertPlayer(player)
                    _error.value = false
                }
            } catch (e: RuntimeException) {
                e.printStackTrace()
                _error.value = true

                Log.e("Error fetching players", e.message.toString())
            }
        }
    }

    fun setLists() {
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

