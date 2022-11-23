package ar.edu.unlam.figuritas.data.api

import ar.edu.unlam.figuritas.model.response.CountryResponse
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import ar.edu.unlam.figuritas.model.response.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerAPI {

    companion object{
        const val API_KEY = "vSbRfhdKaDAMvhyeYGnr6eWRE1OK50Su0MeRwmrHvzw2EE8odedirFdNZgzq"
    }

    @GET("players/{playerId}?api_token=$API_KEY")
    suspend fun searchPlayerById(@Path("playerId") playerId : Int) : Response<PlayerResponse>

    @GET("teams/{countryId}?api_token=$API_KEY&include=squad")
    suspend fun searchPlayersByCountryId(@Path("countryId") countryId : Int) : Response<TeamResponse>

    @GET("countries/{countryId}?api_token=$API_KEY")
    suspend fun searchCountryById(@Path("countryId") countryId: Int) : Response<CountryResponse>

}