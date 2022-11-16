package ar.edu.unlam.figuritas.ui.viewModel

import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.figuritas.data.repository.DatabaseRepository
import ar.edu.unlam.figuritas.model.entities.Player
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FiguritasViewModel @Inject constructor(
    private val playerDBRepository: DatabaseRepository,
) : ViewModel() {

    //private val playerList = MutableLiveData<PlayerResponse?>()
    lateinit var sensorManager: SensorManager
    var playerList = mutableListOf<Player>()
    var list: List<Player> = playerList

    init {
        CoroutineScope(Dispatchers.IO).launch {
            playerList = playerDBRepository.getPlayers()
        }
    }
    fun getplayer() {
        CoroutineScope(Dispatchers.IO).launch {
            playerList = playerDBRepository.getPlayers()
        }
    }

    fun getMessiMock(): MutableList<Player> {
        return mutableListOf(
            Player(123,"nano","ddd","123","123","22/2/00",4),
            Player(123,"nano","ddd","123","123","22/2/00",4),
            Player(123,"nano","ddd","123","123","22/2/00",4),
            Player(123,"nano","ddd","123","123","22/2/00",4),
            Player(123,"nano","ddd","123","123","22/2/00",4),
            Player(123,"nano","ddd","123","123","22/2/00",4)
        )
    }
    /*fun getplayer() {
        viewModelScope.launch {
            val response = playerDBRepository.searchPlayerById(5)
            if (response.isSuccessful && response.body() != null) {
                val player = response.body()
                playerList.value?.add(player)
            } else {
                val error = response.errorBody().toString()
                Log.d("Error response player ", error)
            }
        }

    }*/


}