package ar.edu.unlam.figuritas.data.repository

import ar.edu.unlam.figuritas.data.api.PlayerClient
import ar.edu.unlam.figuritas.model.WorldCupTeamId
import ar.edu.unlam.figuritas.model.response.*
import retrofit2.Response

class PlayerRepository(private val playerClient: PlayerClient) {


    suspend fun getPlayerById(playerId : Int) : Response<PlayerResponse> {
        return playerClient.searchPlayerById(playerId)
    }

    suspend fun getPlayersByCountry(country: WorldCupTeamId) : List<PlayerResponse?>? {
        return getSquadDataByCountry(country)?.map { getPlayerById(it.playerId).body() }
    }

    suspend fun getRandomPlayers(qty: Int) : List<PlayerResponse?> {
        return List(qty) { getPlayerById(getRandomPlayerId()).body() }
    }

    private suspend fun getRandomPlayerId() : Int {
        return getSquadDataByCountry(getRandomCountryId())?.random()?.playerId!!
    }

    private suspend fun getSquadDataByCountry(country: WorldCupTeamId): List<SquadPlayerData>? {
        return playerClient.searchPlayersByCountryId(country.id).body()?.data?.squad?.data
    }


    private fun getRandomCountryId() : WorldCupTeamId {
        return WorldCupTeamId.values().random()
    }
}