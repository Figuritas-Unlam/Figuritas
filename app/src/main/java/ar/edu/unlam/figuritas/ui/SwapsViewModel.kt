package ar.edu.unlam.figuritas.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.figuritas.data.repository.DatabaseRepository
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SwapsViewModel @Inject constructor(private val repository: DatabaseRepository) : ViewModel() {
    private val _swappableStickers = MutableLiveData<List<PlayerEntity>>()

    val swappableStickers : LiveData<List<PlayerEntity>> = _swappableStickers
    init {
        _swappableStickers.value = emptyList()
        viewModelScope.launch {
            _swappableStickers.value = repository.getSwapablePlayers()
        }
    }

    fun addStickers(stickers: List<PlayerEntity>) {
        stickers.forEach { repository.insertPlayerEntity(it) }
    }
}
