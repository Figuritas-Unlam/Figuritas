package ar.edu.unlam.figuritas.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Players")
data class PlayerEntity (
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val playerId : Int,

    @ColumnInfo(name = "Name")
    val playerName : String,

    @ColumnInfo(name = "Height")
    val height : String?,

    @ColumnInfo(name = "Weight")
    val weight : String?,

    @ColumnInfo(name = "Birthdate")
    val birthdate : String,

    @ColumnInfo(name = "TeamId")
    val teamId : Int,

    @ColumnInfo(name = "Seleccion_Id")
    val seleccionId : Int,

    @ColumnInfo(name = "Quantity")
    var quantity : Int,

    @ColumnInfo(name = "In_Album")
    var inAlbum : Boolean,

    @ColumnInfo(name = "Image")
    var imageUrl : String,

    @ColumnInfo(name = "Paste")
    var isPaste : String,

    @ColumnInfo(name = "Position")
    var position : Int
    )


fun PlayerEntity.mapToPlayer()=Player(playerId,
    playerName,height,
    weight,birthdate,
    seleccionId,
    quantity, imageUrl)

data class Player (
    val playerId : Int,
    val playerName : String,
    val height : String?,
    val weight : String?,
    val birthdate : String,
    val seleccionId : Int,
    var quantity : Int,
    var imageUrl : String
)
