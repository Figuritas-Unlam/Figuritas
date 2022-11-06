package ar.edu.unlam.figuritas.model.response

import ar.edu.unlam.figuritas.model.response.PlayerResponse
import com.google.gson.annotations.SerializedName

data class PlayersResponse(

    @SerializedName("data")
    var data : MutableList<PlayerResponse>
)