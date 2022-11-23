package ar.edu.unlam.figuritas.data.repository

import ar.edu.unlam.figuritas.data.database.dao.PlayerDao
import ar.edu.unlam.figuritas.data.database.entities.PlayerEntity
import ar.edu.unlam.figuritas.domain.response.PlayerResponse
import ar.edu.unlam.figuritas.model.entities.PlayerAlbumEntity
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
                isSwappable = false,
                imageUrl = playerResponse.data.image,
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

    fun getPlayersPasteForCountry(countryId : Int) : List<PlayerAlbumEntity>{
        return playerDao.getPlayersInAlbumForIdCountry(countryId)
    }

    fun insertPlayerInAlbum(playerAlbum : PlayerAlbumEntity){
        playerDao.insertPlayerInAlbum(playerAlbum)
    }

    fun getPlayerForId(playerId : Int) : PlayerEntity?{
        return playerDao.searchPlayerForId(playerId)
    }

}