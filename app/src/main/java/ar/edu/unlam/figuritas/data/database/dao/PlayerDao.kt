package ar.edu.unlam.figuritas.data.database.dao

import androidx.room.*
import ar.edu.unlam.figuritas.model.entities.PlayerAlbumEntity
import ar.edu.unlam.figuritas.model.entities.PlayerEntity

@Dao
interface PlayerDao {

    @Query("Select * From Players")
    fun getAllPlayers() : List<PlayerEntity>

    @Query("Select * From Players pa Where pa.Name = :namePlayer ")
    fun getPlayerForName(namePlayer: String) : PlayerEntity

    @Query("Select * From Players pa Where pa.quantity > 1 ")
    fun getRepeatedPlayers() : List<PlayerEntity>

    @Query("Select * From Players pa Where pa.Id = :idPlayer ")
    fun searchPlayerForId(idPlayer: Int): PlayerEntity

    @Query("Select pm.* From Players pa Join PlayerAlbum pm On pa.Id = pm.PlayerId" +
            " Where pa.Seleccion_Id = :countryId")
    fun getPlayersInAlbumForIdCountry(countryId : Int) : List<PlayerAlbumEntity>

    @Query("Select * From Players pa Where pa.Is_Swapable = 1")
    fun getSwappablePlayers() : List<PlayerEntity>

    @Query("UPDATE Players SET Is_Swapable = :swappable WHERE id = :playerId AND Id Not In ( Select pl.playerId From PlayerAlbum pl)")
    fun setSwappablePlayers(playerId: Int, swappable: Boolean)

    @Insert
    fun insertPlayer(entity: PlayerEntity)

    @Insert
    fun insertPlayerInAlbum(entity: PlayerAlbumEntity)

    @Query("SELECT * from Players WHERE id= :id AND Quantity > 0 And Id Not In ( Select pl.playerId From PlayerAlbum pl)")
    fun getPlayerById(id: Int) : PlayerEntity?

    @Query("SELECT * from Players WHERE id= :id And Quantity > 1 And Id Not In ( Select pl.playerId From PlayerAlbum pl)")
    fun getDuplicatedPlayerById(id: Int) : PlayerEntity?

    @Query("UPDATE Players SET quantity = quantity + :qty WHERE id = :id")
    fun updateQuantity(id: Int, qty: Int)

    fun insertOrUpdate(player: PlayerEntity) {
        val playerFromDB = getPlayerById(player.playerId)
        if (playerFromDB == null) insertPlayer(player)
        else updateQuantity(player.playerId, 1)
    }

    @Delete
    fun deletePlayer(entity: PlayerEntity)

    fun deleteOrUpdate(player: PlayerEntity) {
        val playerFromDB = getDuplicatedPlayerById(player.playerId)
        if (playerFromDB == null) deletePlayer(player)
        else updateQuantity(player.playerId, -1)
    }


    @Query("Update Players Set Quantity = Quantity - 1 Where Id = :idPlayer")
    fun restQuantity(idPlayer: Int)

    @Query("Select * From Players pa Where pa.Id Not In ( Select pl.playerId From PlayerAlbum pl)")
    fun getPlayersNotPaste(): List<PlayerEntity>

    @Query("Select * From Players pa Where  pa.Quantity>1")
    fun getRepeats(): List<PlayerEntity>

}