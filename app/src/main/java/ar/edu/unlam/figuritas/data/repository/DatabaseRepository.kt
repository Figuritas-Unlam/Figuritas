package ar.edu.unlam.figuritas.data.repository

import ar.edu.unlam.figuritas.data.database.dao.PlayerDao
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val playerDao: PlayerDao) {

    fun searchAllPlayers() : List<PlayerEntity>{
        return playerDao.getAllPlayers()
    }
}