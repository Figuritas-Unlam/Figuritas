package ar.edu.unlam.figuritas.data.database.dao

import androidx.room.*
import ar.edu.unlam.figuritas.model.entities.PlayerEntity

@Dao
interface PlayerDao {

    @Query("Select * From Players")
    fun getAllPlayers() : List<PlayerEntity>

    @Query("Select * From Players pa Where pa.Name = :namePlayer ")
    fun getPlayerForName(namePlayer: String) : PlayerEntity

    @Query("Select * From Players pa Where pa.quantity > 1 ")
    fun getRepeatedPlayers() : List<PlayerEntity>

    @Insert
    fun insertPlayer(entity: PlayerEntity)

    @Query("SELECT * from Players WHERE id= :id")
    fun getPlayerById(id: Int) : PlayerEntity?

    @Query("UPDATE Players SET quantity = quantity + 1 WHERE id = :id")
    fun updateQuantity(id: Int)

    fun insertOrUpdate(player: PlayerEntity) {
        val playerFromDB = getPlayerById(player.playerId)
        if (playerFromDB == null) insertPlayer(player)
        else updateQuantity(player.playerId)
    }

    @Delete
    fun deletePlayer(entity: PlayerEntity)

    @Query("UPDATE Players SET in_album = 1 WHERE id = :id")
    fun placePlayerInAlbum(id: Int)

}