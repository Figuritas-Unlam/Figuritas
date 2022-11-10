package ar.edu.unlam.figuritas.data.api

import ar.edu.unlam.figuritas.model.response.PlayerResponse
import ar.edu.unlam.figuritas.model.response.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface PlayerAPI {

    companion object{
        const val API_KEY = "3puVV9TkLHqxgI5xrd00AOS16Zxs2IdcSJLhGciUdRuzswDOvN4KuUVtZuHo"
    }

    @GET("players/{playerId}?api_token=$API_KEY")
    suspend fun searchPlayerById(@Path("playerId") playerId : Int) : Response<PlayerResponse>

    @GET("teams/{countryId}?api_token=$API_KEY&include=squad")
    suspend fun searchPlayersByCountryId(@Path("countryId") countryId : Int) : Response<TeamResponse>
}