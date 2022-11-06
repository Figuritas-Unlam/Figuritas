package ar.edu.unlam.figuritas.data.repository

import ar.edu.unlam.figuritas.data.api.PlayerAPI
import ar.edu.unlam.figuritas.data.api.PlayerClient
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import retrofit2.Response

class PlayerRepository(private val playerClient: PlayerClient) {


    suspend fun searchPlayerById(playerId : Int) : Response<PlayerResponse> {
        return playerClient.searchPlayerById(playerId)
    }
}