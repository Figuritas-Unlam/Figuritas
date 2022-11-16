package ar.edu.unlam.figuritas.data.database.dao

import androidx.room.*
import ar.edu.unlam.figuritas.model.entities.PlayerEntity

@Dao
interface PlayerDao {

    @Query("Select * From Players")
    fun getAllPlayers() : List<PlayerEntity>

    @Query("Select * From Players pa Where pa.Name = :namePlayer ")
    fun searchPlayerForName(namePlayer: String) : PlayerEntity


    @Query("Select * From Players pa Where pa.Id = :idPlayer ")
    fun searchPlayerForId(idPlayer: String) : PlayerEntity

    @Query("Select Count(*) From Players Where Id = :idPlayer")
    fun countRepetidas(idPlayer : String) : Int

    @Insert
    fun insertPlayer(entity: PlayerEntity)

    @Delete
    fun deletePlayer(entity: PlayerEntity)

    @Query("Update Players Set Quantity = Quantity + 1 Where Id = :idPlayer")
    fun sumQuantity(idPlayer: String)

}