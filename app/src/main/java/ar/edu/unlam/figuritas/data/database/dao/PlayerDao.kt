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

    @Query("Select * From Players pa Where pa.Id = :idPlayer ")
    fun searchPlayerForId(idPlayer: Int): PlayerEntity

    @Query("Select * From Players pa Where pa.Seleccion_Id = :countryId")
    fun getPlayersForIdCountry(countryId : Int) : List<PlayerEntity>
    @Query("Select * From Players pa Where pa.Is_Swapable = 1")
    fun getSwappablePlayers() : List<PlayerEntity>

    @Query("UPDATE Players SET Is_Swapable = :swappable WHERE id = :playerId AND In_Album = 0")
    fun setSwappablePlayers(playerId: Int, swappable: Boolean)

    @Insert
    fun insertPlayer(entity: PlayerEntity)

    @Query("SELECT * from Players WHERE id= :id AND In_Album = 0 And Quantity > 0")
    fun getPlayerById(id: Int) : PlayerEntity?

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
        val playerFromDB = getPlayerById(player.playerId)
        if (playerFromDB == null) deletePlayer(player)
        else updateQuantity(player.playerId, -1)
    }

    @Query("UPDATE Players SET in_album = 1 WHERE id = :id")
    fun placePlayerInAlbum(id: Int)

    @Query("Update Players Set Quantity = Quantity - 1 Where Id = :idPlayer")
    fun restQuantity(idPlayer: Int)

    @Query("Select * From Players pa Where pa.Paste = 'NotPaste' ")
    fun getPlayersNotPaste(): List<PlayerEntity>

    @Query("Select * From Players pa Where (pa.Paste = 'Paste' And pa.Quantity>0) Or (pa.Paste = 'NotPaste' And pa.Quantity>1)")
    fun getRepeats(): List<PlayerEntity>

    @Query("Update Players Set Paste = 'Paste', Quantity = Quantity-1 Where Id = :idPlayer")
    fun pastePlayer(idPlayer: Int)

    @Query("Select * From Players pa Where pa.Paste = 'Paste'")
    fun getPlayersPaste() : List<PlayerEntity>
}