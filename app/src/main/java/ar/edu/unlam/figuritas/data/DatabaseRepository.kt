package ar.edu.unlam.figuritas.data

import ar.edu.unlam.figuritas.data.database.dao.PlayerDao
import ar.edu.unlam.figuritas.data.database.entities.PlayerEntity
import ar.edu.unlam.figuritas.domain.response.PlayerResponse
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val playerDao: PlayerDao) {


    fun insertPlayer(playerResponse: PlayerResponse) {
        playerDao.insertOrUpdate(
            PlayerEntity(
                playerResponse.data.playerId,
                playerResponse.data.name,
                playerResponse.data.height,
                playerResponse.data.weight,
                playerResponse.data.birthdate,
                playerResponse.data.nationality,
                playerResponse.data.teamId,
                playerResponse.data.countryId,
                quantity = 1,
                inAlbum = false,
                isSwappable = false,
                imageUrl = playerResponse.data.image,
                "NotPaste",
                playerResponse.data.imageCountry
            )
        )
    }
    fun getPlayersNotPaste(): List<PlayerEntity?> {
        return playerDao.getPlayersNotPaste()
    }

    fun getRepeats(): List<PlayerEntity?> {
        return playerDao.getRepeats()
    }

    fun insertPlayerEntity(player : PlayerEntity) {
        playerDao.insertOrUpdate(player)
    }

    fun getSwapablePlayers(): List<PlayerEntity> {
        return playerDao.getSwappablePlayers()
    }

    fun getallPlayers(): List<PlayerEntity> {
        return playerDao.getAllPlayers()
    }

    fun deletePlayers(players: List<PlayerEntity>?) {
        players?.forEach { playerDao.deleteOrUpdate(it) }
    }
}