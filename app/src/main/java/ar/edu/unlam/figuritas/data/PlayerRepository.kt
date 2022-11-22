package ar.edu.unlam.figuritas.data

import ar.edu.unlam.figuritas.data.api.PlayerClient
import ar.edu.unlam.figuritas.data.database.dao.PlayerDao
import ar.edu.unlam.figuritas.domain.WorldCupTeamId
import ar.edu.unlam.figuritas.data.database.entities.PlayerEntity
import ar.edu.unlam.figuritas.domain.response.PlayerResponse
import ar.edu.unlam.figuritas.domain.response.SquadPlayerData
import retrofit2.Response
import javax.inject.Inject

class PlayerRepository @Inject constructor(
    private val playerClient: PlayerClient,
    private val playerDao: PlayerDao
) {

    suspend fun getPlayerById(playerId : Int) : Response<PlayerResponse> {
        return playerClient.searchPlayerById(playerId)
    }

    suspend fun getPlayersByCountry(country: WorldCupTeamId) : List<PlayerResponse?>? {
        return getSquadDataByCountry(country)?.map { getPlayerById(it.playerId).body() }
    }

    suspend fun getRandomPlayers(qty: Int) : List<PlayerResponse?> {
        return List(qty) {
            getPlayerById(getRandomPlayerId()).body()
        }
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

    suspend fun getCountryById(countryId : Int) : CountryResponse?{
        return playerClient.searchCountryById(countryId).body()
    }

    fun searchAllPlayers() : List<PlayerEntity>{
        return playerDao.getAllPlayers()
    }
}