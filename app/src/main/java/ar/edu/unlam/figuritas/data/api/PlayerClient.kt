package ar.edu.unlam.figuritas.data.api

import ar.edu.unlam.figuritas.model.response.PlayerResponse
import ar.edu.unlam.figuritas.model.response.SquadResponse
import ar.edu.unlam.figuritas.model.response.TeamsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlayerClient {

    private val servicePlayerApi : PlayerAPI = Retrofit.Builder()
        .baseUrl("https://api.sportmonks.com/v3/football/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PlayerAPI::class.java)

    suspend fun searchPlayerById(playerId : Int) : Response<PlayerResponse>{
        return servicePlayerApi.searchPlayer(playerId)
    }

    suspend fun searchTeamByName(teamName : String) : Response<TeamsResponse>{
        return servicePlayerApi.searchTeamByName(teamName)
    }

    suspend fun searchSquadByTeamId(teamId : Int) : Response<SquadResponse>{
        return servicePlayerApi.searchSquadByIdTeam(teamId)
    }
}