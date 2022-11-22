package ar.edu.unlam.figuritas.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.figuritas.data.DatabaseRepository
import ar.edu.unlam.figuritas.data.PlayerRepository
import ar.edu.unlam.figuritas.data.database.entities.PlayerEntity
import ar.edu.unlam.figuritas.domain.response.PlayerResponse
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
        val player = databaseRepository.getRepeats()
        return if (player.isEmpty()) {
            playerRandomEntity()
        } else {
            player.first()
        }

    }


    fun getFirstPlayerNueva(): PlayerEntity? {
        val player = databaseRepository.getPlayersNotPaste()
        return if (player.isEmpty()) {
            playerRandomEntity()
        } else {
            player.first()
        }
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
                    if (player != null) {
                        databaseRepository.insertPlayer(player)
                    }
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


    private fun insertPlayerDatabase(players: List<PlayerResponse?>) {
        for (player in players) {
            viewModelScope.launch {
                databaseRepository.insertPlayer(player!!)
            }
        }
    }

    fun playerRandomEntity(): PlayerEntity {
        return PlayerEntity(
            1,
            "Sin Figuritas",
            "1",
            "1",
            "19/09/2020",
            "FIFA",
            1,
            1,
            quantity = 1,
            inAlbum = false,
            isSwappable = false,
            "data:image/png;base64,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",
            "NotPaste",
            "data:image/png;base64,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"
        )
    }
}