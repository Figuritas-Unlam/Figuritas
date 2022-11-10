package ar.edu.unlam.figuritas.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ar.edu.unlam.figuritas.model.entities.PlayerEntity

@Entity(tableName = "Repeated",
    foreignKeys = [ForeignKey(entity = PlayerEntity::class,
        parentColumns = arrayOf("Id"),
        childColumns = arrayOf("PlayerId"),
        onDelete = ForeignKey.CASCADE)])
data class RepeatedEntity (
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id : Int,

    @ColumnInfo(name = "PlayerId")
    val playerId : Int
)