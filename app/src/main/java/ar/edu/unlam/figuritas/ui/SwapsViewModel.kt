package ar.edu.unlam.figuritas.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.figuritas.data.repository.DatabaseRepository
import ar.edu.unlam.figuritas.model.PlayerModel
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SwapsViewModel @Inject constructor(private val repository: DatabaseRepository) : ViewModel() {
    private val _swappableStickers = MutableLiveData<List<PlayerModel>>()
    val swappableStickers : LiveData<List<PlayerModel>> = _swappableStickers

    init {
        _swappableStickers.value = emptyList()
        viewModelScope.launch {
            _swappableStickers.value = repository.getSwapablePlayers().map { PlayerModel.of(it) }
        }
    }

    fun addStickers(stickers: List<PlayerModel>) {
        stickers.forEach { repository.insertPlayerEntity(it.toDataModel()) }
    }

    fun getSelectedStickers(): List<PlayerModel> {
        val selectedStickers = _swappableStickers.value?.filter { it.isSelected }
        repository.deletePlayers(selectedStickers.toDataModel())
        _swappableStickers.value = repository.getSwapablePlayers().map { PlayerModel.of(it) }
        return selectedStickers ?: emptyList()
    }
}

private fun List<PlayerModel>?.toDataModel(): List<PlayerEntity> {
    return this?.map {
        PlayerEntity(
            playerId = it.playerId,
            playerName = it.playerName,
            height = it.height,
            weight = it.weight,
            birthdate = it.birthdate,
            nationality = it.nationality,
            teamId = it.teamId,
            seleccionId = it.seleccionId,
            quantity = it.quantity,
            inAlbum = it.inAlbum,
            imageUrl = it.imageUrl,
            isSwappable = it.isSwappable
        )
    } ?: emptyList()
}

private fun PlayerModel.toDataModel(): PlayerEntity {
    return PlayerEntity(
        playerId = this.playerId,
        playerName = this.playerName,
        height = this.height,
        weight = this.weight,
        birthdate = this.birthdate,
        nationality = this.nationality,
        teamId = this.teamId,
        seleccionId = this.seleccionId,
        quantity = this.quantity,
        inAlbum = this.inAlbum,
        imageUrl = this.imageUrl,
        isSwappable = this.isSwappable
    )
}