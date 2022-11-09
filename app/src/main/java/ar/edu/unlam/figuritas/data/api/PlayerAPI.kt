package ar.edu.unlam.figuritas.data.api

import ar.edu.unlam.figuritas.model.response.PlayerResponse
import ar.edu.unlam.figuritas.model.response.SquadResponse
import ar.edu.unlam.figuritas.model.response.TeamsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface PlayerAPI {

    companion object{
        const val API_KEY = "3puVV9TkLHqxgI5xrd00AOS16Zxs2IdcSJLhGciUdRuzswDOvN4KuUVtZuHo"
    }


    @GET("players/{playerId}}?api_token=$API_KEY")
    suspend fun searchPlayer(@Path("playerId") playerId : Int) : Response<PlayerResponse>

    @GET("teams/search/{teamName}?api_token=$API_KEY")
    suspend fun searchTeamByName(@Path("teamName") teamName : String) : Response<TeamsResponse>

    @GET("squads/teams/{teamId}?api_token=$API_KEY&include=player")
    suspend fun searchSquadByIdTeam(@Path("teamId") teamId : Int) : Response<SquadResponse>
}