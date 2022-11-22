package ar.edu.unlam.figuritas.data.database.entities

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

    @ColumnInfo(name = "Nationality")
    val nationality : String,

    @ColumnInfo(name = "TeamId")
    val teamId : Int,

    @ColumnInfo(name = "Seleccion_Id")
    val seleccionId : Int,

    @ColumnInfo(name = "Quantity")
    var quantity : Int,

    @ColumnInfo(name = "In_Album")
    var inAlbum : Boolean,

    @ColumnInfo(name = "Is_Swapable")
    var isSwappable : Boolean,

    @ColumnInfo(name = "Image")
    var imageUrl : String
)