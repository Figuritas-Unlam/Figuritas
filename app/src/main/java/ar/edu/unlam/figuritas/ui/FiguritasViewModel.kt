package ar.edu.unlam.figuritas.ui

import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.figuritas.data.api.PlayerClient
import ar.edu.unlam.figuritas.domain.response.PlayerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FiguritasViewModel @Inject constructor(
    private val playerClient: PlayerClient,
) : ViewModel() {

    //private val playerList = MutableLiveData<PlayerResponse?>()
    lateinit var sensorManager: SensorManager
    val playerList = MutableLiveData<MutableList<PlayerResponse?>>()


    fun getplayer() {
        viewModelScope.launch {
            val response = playerClient.searchPlayerById(5)
            if (response.isSuccessful && response.body() != null) {
                val player = response.body()
                playerList.value?.add(player)
            } else {
                val error = response.errorBody().toString()
                Log.d("Error response player ", error)
            }
        }

    }


}