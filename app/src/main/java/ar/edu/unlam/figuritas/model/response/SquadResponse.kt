package ar.edu.unlam.figuritas.model.response

import com.google.gson.annotations.SerializedName

data class SquadResponse (
    @SerializedName("data")
    var data : List<PlayerSquadResponse>
)

data class PlayerSquadResponse(
    @SerializedName("id")
    var id : Int,
    @SerializedName("start")
    var start : String,
    @SerializedName("end")
    var end : String,
    @SerializedName("yersey_number")
    var positionNumber : Int,
    @SerializedName("player")
    var dataPlayer : PlayerResponse
)
