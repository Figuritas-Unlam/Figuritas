package ar.edu.unlam.figuritas.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import ar.edu.unlam.figuritas.model.entities.RepeatedEntity

@Dao
interface RepeatedDao {

    @Query("Select re.* From Repeated re")
    fun getAllRepeated() : List<RepeatedEntity>

    @Query("Select Distinct pa.Id, pa.Name, pa.Birthdate, " +
            "pa.Height, pa.Weight, pa.Seleccion_Id, pa.TeamId " +
            "From Repeated re Join Players pa On re.PlayerId = pa.Id")
    fun getAllPlayers() : List<PlayerEntity>

    @Query("Select * From Repeated re Where re.PlayerId = :idPlayer ")
    fun searchRepeatedForIdPlayer(idPlayer: String) : RepeatedEntity

    @Query("Select pa.* From Repeated re " +
            "Join Players pa On re.PlayerId = pa.Id " +
            "Where re.PlayerId = :idRepeated ")
    fun searchPlayerForIdRepeated(idRepeated: String) : PlayerEntity

    @Query("Select Count(re.Id) From Repeated re Where re.PlayerId = :idPlayer")
    fun searchQuantityRepeated(idPlayer: String) : Int

    @Insert
    fun insertRepeated(entity: RepeatedEntity)

    @Delete
    fun deleteRepeated(entity: RepeatedEntity)
}