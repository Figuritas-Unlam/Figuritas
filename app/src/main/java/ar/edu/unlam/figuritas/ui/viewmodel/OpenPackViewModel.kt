package ar.edu.unlam.figuritas.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.figuritas.data.repository.PlayerRepository
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import kotlinx.coroutines.launch

class OpenPackViewModel(repository: PlayerRepository): ViewModel() {
    private val _playerData = MutableLiveData<List<PlayerResponse?>>()
    val playerData:LiveData<List<PlayerResponse?>> = _playerData

    init {
        viewModelScope.launch {
            _playerData.value = repository.getRandomPlayers(5)
        }
    }
}