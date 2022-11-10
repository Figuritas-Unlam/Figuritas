package ar.edu.unlam.figuritas.model.response

import com.google.gson.annotations.SerializedName

class TeamResponse(
    @SerializedName("data")
    val data : TeamResponseData
)
class TeamResponseData (
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("short_code")
    val shortName : String,
    @SerializedName("logo_path")
    val imageUrl : String,
    @SerializedName("founded")
    val foundedYear : Int,
    @SerializedName("squad")
    val squad : TeamSquad,
)

data class TeamSquad(
    val data: List<SquadPlayerData>
)

data class SquadPlayerData(
    @SerializedName("player_id")
    val playerId: Int,
    @SerializedName("position_id")
    val positionId: Int
)
