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
    val height : String,

    @ColumnInfo(name = "Weight")
    val weight : String,

    @ColumnInfo(name = "Birthdate")
    val birthdate : String,

    @ColumnInfo(name = "TeamId")
    val teamId : Int,

    @ColumnInfo(name = "Seleccion_Id")
    val seleccionId : Int,

    @ColumnInfo(name = "Quantity")
    var quantity : Int,

    @ColumnInfo(name = "image")
    val imageUrl: String,

    @ColumnInfo(name = "In_Album")
    var inAlbum : Boolean
    )
fun PlayerEntity.mapToPlayer(): Player=Player(this.playerId,
    this.playerName,
    this.imageUrl,
    this.height,
    this.weight,
    this.birthdate,
    this.quantity)
data class Player (
    val playerId : Int,
    val playerName : String,
    val imageUrl: String,
    val height : String,
    val weight : String,
    val birthdate : String,
    var quantity : Int,
)