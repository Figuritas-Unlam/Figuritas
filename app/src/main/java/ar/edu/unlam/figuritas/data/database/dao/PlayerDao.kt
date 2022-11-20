package ar.edu.unlam.figuritas.data.database.dao

import androidx.room.*
import ar.edu.unlam.figuritas.model.entities.PlayerEntity

@Dao
interface PlayerDao {

    @Query("Select * From Players")
    fun getAllPlayers(): List<PlayerEntity>

    @Query("Select * From Players pa Where pa.Name = :namePlayer ")
    fun searchPlayerForName(namePlayer: String): PlayerEntity


    @Query("Select * From Players pa Where pa.Id = :idPlayer ")
    fun searchPlayerForId(idPlayer: Int): PlayerEntity

    @Query("Select Exists(Select pa.* From Players pa Where pa.Id = :idPlayer)")
    fun isPlayerExists(idPlayer: Int): Boolean

    @Insert
    fun insertPlayer(entity: PlayerEntity)

    @Delete
    fun deletePlayer(entity: PlayerEntity)

    @Query("Update Players Set Quantity = Quantity + 1 Where Id = :idPlayer")
    fun sumQuantity(idPlayer: Int)

}