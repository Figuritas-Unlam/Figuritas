package ar.edu.unlam.figuritas.data.repository

import ar.edu.unlam.figuritas.data.database.dao.PlayerDao
import ar.edu.unlam.figuritas.model.entities.Player
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import ar.edu.unlam.figuritas.model.entities.mapToPlayer
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import java.util.Optional.empty
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val playerDao: PlayerDao) {


    fun insertPlayer(playerResponse : PlayerResponse){
        val idPlayerResponse = playerResponse.data.playerId.toString()

        if(playerDao.countRepetidas(idPlayerResponse) == 0)
        {
        playerDao.insertPlayer(PlayerEntity(playerResponse.data.playerId, playerResponse.data.name,
        playerResponse.data.height, playerResponse.data.weight, playerResponse.data.birthdate,
            playerResponse.data.teamId, playerResponse.data.countryId, 0,playerResponse.data.image, false))
        }
        else
        {
            playerDao.sumQuantity(idPlayerResponse)
        }
    }

    suspend fun getPlayers(): MutableList<Player>{
        val players= mutableListOf<Player>()
        val entityPlayers=playerDao.getAllPlayers()
        entityPlayers.forEach {
            players.add(it.mapToPlayer())
        }
        return players
    }
}