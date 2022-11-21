package ar.edu.unlam.figuritas.data.api

import ar.edu.unlam.figuritas.model.response.CountryResponse
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import ar.edu.unlam.figuritas.model.response.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerAPI {

    companion object{
        const val API_KEY = "fYqTB9XfINa59yGPulaC7Ui2dUz2q94UTSrAtAJkEy20m9Z0DwHAC6fP0G2Q"
    }

    @GET("players/{playerId}?api_token=$API_KEY")
    suspend fun searchPlayerById(@Path("playerId") playerId : Int) : Response<PlayerResponse>

    @GET("teams/{countryId}?api_token=$API_KEY&include=squad")
    suspend fun searchPlayersByCountryId(@Path("countryId") countryId : Int) : Response<TeamResponse>

    @GET("countries/{countryId}?api_token=$API_KEY")
    suspend fun searchCountryById(@Path("countryId") countryId: Int) : Response<CountryResponse>
}