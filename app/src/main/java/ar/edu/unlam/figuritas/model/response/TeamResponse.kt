package ar.edu.unlam.figuritas.model.response

import com.google.gson.annotations.SerializedName

class TeamsResponse(

    @SerializedName("data")
    var data : List<TeamResponse>
)
class TeamResponse (

    @SerializedName("id")
    var id : Int,

    @SerializedName("country_id")
    var country_id : Int,

    @SerializedName("name")
    var name : String,

    @SerializedName("short_code")
    var shortName : String,

    @SerializedName("image_path")
    var image : String

    )