package ar.edu.unlam.figuritas.data.api

import ar.edu.unlam.figuritas.model.response.PlayerResponse
import ar.edu.unlam.figuritas.model.response.SquadResponse
import ar.edu.unlam.figuritas.model.response.TeamsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class PlayerClient @Inject constructor(
    private val servicePlayerApi: PlayerAPI,
) {



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