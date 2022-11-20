package ar.edu.unlam.figuritas.data.repository

import ar.edu.unlam.figuritas.data.database.dao.PlayerDao
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val playerDao: PlayerDao) {


    fun insertPlayer(playerResponse : PlayerResponse){
        playerDao.insertOrUpdate(
            PlayerEntity(
                playerResponse.data.playerId,
                playerResponse.data.name,
                playerResponse.data.height,
                playerResponse.data.weight,
                playerResponse.data.birthdate,
                playerResponse.data.teamId,
                playerResponse.data.countryId,
                1,
                false,
                playerResponse.data.image
            )
        )
    }

    fun getallPlayers(): List<PlayerEntity> {
        return playerDao.getAllPlayers()
    }
}