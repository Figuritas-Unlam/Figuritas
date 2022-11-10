package ar.edu.unlam.figuritas.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.figuritas.model.entities.PlayerEntity

@Dao
interface PlayerDao {

    @Query("Select * From Players")
    fun getAllPlayers() : List<PlayerEntity>

    @Query("Select * From Players pa Where pa.Name = :namePlayer ")
    fun searchPlayerForName(namePlayer: String) : PlayerEntity


    @Query("Select * From Players pa Where pa.Name = :idPlayer ")
    fun searchPlayerForId(idPlayer: String) : PlayerEntity

    @Insert
    fun insertPlayer(entity: PlayerEntity)

    @Delete
    fun deletePlayer(entity: PlayerEntity)

}