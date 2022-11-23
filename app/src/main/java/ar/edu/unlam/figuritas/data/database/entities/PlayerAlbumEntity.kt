package ar.edu.unlam.figuritas.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "PlayerAlbum",
    foreignKeys = [ForeignKey(entity = PlayerEntity::class,
        parentColumns = arrayOf("Id"),
        childColumns = arrayOf("PlayerId"),
        onDelete = ForeignKey.CASCADE)]
)
data class PlayerAlbumEntity (

    @PrimaryKey
    @ColumnInfo(name = "PlayerId")
    val playerId : Int,

    @ColumnInfo(name = "Position")
    val position : Int
)