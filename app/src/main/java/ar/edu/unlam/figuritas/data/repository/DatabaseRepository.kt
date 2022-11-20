package ar.edu.unlam.figuritas.data.repository

import android.util.Log
import ar.edu.unlam.figuritas.data.database.dao.PlayerDao
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val playerDao: PlayerDao) {


    fun insertPlayer(playerResponse: PlayerResponse): Boolean {
        val idPlayerResponse = playerResponse.data.playerId
        if (!playerDao.isPlayerExists(idPlayerResponse)) {
            playerDao.insertPlayer(
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
            Log.e("insert", "insert correcto")
            return true
        } else {
            playerDao.sumQuantity(idPlayerResponse)
            return false
        }
    }

    fun getallPlayers(): List<PlayerEntity> {
        return playerDao.getAllPlayers()

    }

    fun getPlayer (id : Int ): PlayerEntity{
        return playerDao.searchPlayerForId(id)
    }

    fun sumQuantity(idPlayer: Int){
        playerDao.sumQuantity(idPlayer)
    }
}