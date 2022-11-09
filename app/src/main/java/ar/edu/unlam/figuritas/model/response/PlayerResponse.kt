package ar.edu.unlam.figuritas.model.response

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class PlayerResponse(

    @SerializedName("id")
    var id: Int,

    @SerializedName("player_id")
    var playerId: Int,

    @SerializedName("country_id")
    var countryId: Int,

    @SerializedName("team_id")
    var teamId: Int,

    @SerializedName("display_name")
    var name: String,

    @SerializedName("fullname")
    var fullname: String,

    @SerializedName("height")
    var height: Double,

    @SerializedName("weight")
    var weight: Double,

    @SerializedName("birthdate")
    var birthdate: String,

    @SerializedName("nationality")
    var nationality: String,

    @SerializedName("image_path")
    var image: String,

    @SerializedName("position")
    var position: Position
)

data class Position(

    @SerializedName("id")
    var id: Int,

    @SerializedName("developer_name")
    var name: String
)


data class MockPlayerProvisorio(
    var name: String,
    @DrawableRes var image: Int
)