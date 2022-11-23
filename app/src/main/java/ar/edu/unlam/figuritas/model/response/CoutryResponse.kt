package ar.edu.unlam.figuritas.model.response

import com.google.gson.annotations.SerializedName

data class CountryResponse(

    @SerializedName("data")
    var data : CountryDataResponse
)

data class CountryDataResponse(

    @SerializedName("id")
    var id : Int,

    @SerializedName("name")
    var name : String,

    @SerializedName("image_path")
    var image : String
)