package ar.edu.unlam.figuritas.data.repository

import ar.edu.unlam.figuritas.data.api.PlayerClient
import ar.edu.unlam.figuritas.model.WorldCupTeamsId
import ar.edu.unlam.figuritas.model.response.*
import retrofit2.Response

class PlayerRepository(private val playerClient: PlayerClient) {


    suspend fun getPlayerById(playerId : Int) : Response<PlayerResponse> {
        return playerClient.searchPlayerById(playerId)
    }

    suspend fun getPlayersIdsByCountry(country: WorldCupTeamsId) : Response<TeamResponse> {
        return playerClient.searchPlayerByCountryId(country.id)
    }

    suspend fun get5RandomPlayers() : List<PlayerResponse?> {
        return List(5) {
            getPlayerById(
                getRandomPlayerId()
            ).body()
        }
    }

    private suspend fun getRandomPlayerId() : Int {
        return playerClient.searchPlayerByCountryId(
            getRandomCountryId()
        ).body()?.data?.squad?.data?.random()?.playerId!!
    }

    private fun getRandomCountryId() : Int {
        return WorldCupTeamsId.values().random().id
    }
}