package ar.edu.unlam.figuritas.ui

import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.figuritas.data.repository.DatabaseRepository
import ar.edu.unlam.figuritas.data.repository.PlayerRepository
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpenPackViewModel @Inject constructor(private val repository: PlayerRepository,
        private val databaseRepository: DatabaseRepository): ViewModel() {
    private val _playersData = MutableLiveData<List<PlayerResponse?>>()
    val playersData:LiveData<List<PlayerResponse?>> = _playersData

    init {
        fetchPlayers()
    }


    private fun fetchPlayers() {
        viewModelScope.launch {
            try {
                _playersData.value = repository.getRandomPlayers(5)
                insertPlayerDatabase(_playersData.value)
            } catch (e: RuntimeException) {
                e.printStackTrace()
                Log.e("Error fetching players", e.message.toString())
            }
        }
    }

    private fun insertPlayerDatabase(players : List<PlayerResponse?>?)
    {
        for(player in players!!){
            viewModelScope.launch {
                databaseRepository.insertPlayer(player!!)
            }
        }
    }
}