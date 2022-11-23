package ar.edu.unlam.figuritas.domain.response

import ar.edu.unlam.figuritas.data.database.entities.PlayerEntity
import com.google.gson.annotations.SerializedName

data class PlayerResponse(
    @SerializedName("data")
    var data : PlayerResponseData
)

data class PlayerResponseData (
    @SerializedName("player_id")
    var playerId : Int,
    @SerializedName("team_id")
    var teamId : Int,
    @SerializedName("country_id")
    var countryId : Int,
    @SerializedName("display_name")
    var name : String,
    @SerializedName("fullname")
    var fullname : String,
    @SerializedName("height")
    var height : String,
    @SerializedName("weight")
    var weight : String,
    @SerializedName("birthdate")
    var birthdate : String,
    @SerializedName("nationality")
    var nationality : String,
    @SerializedName("image_path")
    var image : String,
    @SerializedName("position")
    var position: PositionData,
    var imageCountry: String
)

data class PositionData(
    val data: Position
)

data class Position(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
)


fun PlayerResponse.mapToEntity(quantity: Int, inAlbum: Boolean, isPaste: String) = PlayerEntity(
    data.playerId,
    data.name,
    data.height,
    data.weight,
    data.birthdate,
    data.nationality,
    data.teamId,
    0,
    1,
    false,
    false,
    data.image,
    ""
)