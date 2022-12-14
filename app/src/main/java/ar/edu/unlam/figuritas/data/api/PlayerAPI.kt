package ar.edu.unlam.figuritas.data.api

import ar.edu.unlam.figuritas.model.response.CountryResponse
import ar.edu.unlam.figuritas.domain.response.PlayerResponse
import ar.edu.unlam.figuritas.domain.response.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerAPI {

    companion object{
//        const val API_KEY = "M0To6SUZNekgxV2duNiRFX1ukMLfSBNhTo0WuZnn1aQQnpwLbKORHDP3ocSq"
//        const val API_KEY = "fYqTB9XfINa59yGPulaC7Ui2dUz2q94UTSrAtAJkEy20m9Z0DwHAC6fP0G2Q"
        const val API_KEY = "3puVV9TkLHqxgI5xrd00AOS16Zxs2IdcSJLhGciUdRuzswDOvN4KuUVtZuHo"
    }

    @GET("players/{playerId}?api_token=$API_KEY")
    suspend fun searchPlayerById(@Path("playerId") playerId : Int) : Response<PlayerResponse>

    @GET("teams/{countryId}?api_token=$API_KEY&include=squad")
    suspend fun searchPlayersByCountryId(@Path("countryId") countryId : Int) : Response<TeamResponse>

    @GET("countries/{countryId}?api_token=$API_KEY")
    suspend fun searchCountryById(@Path("countryId") countryId: Int) : Response<CountryResponse>

}