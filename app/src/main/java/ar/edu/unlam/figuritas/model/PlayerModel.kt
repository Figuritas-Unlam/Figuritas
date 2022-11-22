package ar.edu.unlam.figuritas.model

import ar.edu.unlam.figuritas.model.entities.PlayerEntity

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
    val inAlbum : Boolean,
    val isSwappable : Boolean,
    val imageUrl : String,
    val isPaste : String,
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
                inAlbum = playerEntity.inAlbum,
                isSwappable = playerEntity.isSwappable,
                imageUrl = playerEntity.imageUrl,
                isPaste = playerEntity.isPaste,
                imageCountry = playerEntity.imageCountry,

                isSelected = false
            )
        }
    }
}
