package ar.edu.unlam.figuritas.domain.model

import ar.edu.unlam.figuritas.data.database.entities.PlayerEntity

data class PlayerModel (
    val playerId : Int,
    val playerName : String,
    val height : String?,
    val weight : String?,
    val birthdate : String,
    val nationality : String,
    val teamId : Int,
    val seleccionId : Int,
    val quantity : Int,
    val isSwappable : Boolean,
    val imageUrl : String,
    val imageCountry : String,
    var isSelected: Boolean
) {
    companion object {
        fun of(playerEntity: PlayerEntity): PlayerModel {
            return PlayerModel(
                playerId = playerEntity.playerId,
                playerName = playerEntity.playerName,
                height = playerEntity.height,
                weight = playerEntity.weight,
                birthdate = playerEntity.birthdate,
                nationality = playerEntity.nationality,
                teamId = playerEntity.teamId,
                seleccionId = playerEntity.seleccionId,
                quantity = playerEntity.quantity,
                isSwappable = playerEntity.isSwappable,
                imageUrl = playerEntity.imageUrl,
                imageCountry = playerEntity.imageCountry,
                isSelected = false
            )
        }
    }
}
