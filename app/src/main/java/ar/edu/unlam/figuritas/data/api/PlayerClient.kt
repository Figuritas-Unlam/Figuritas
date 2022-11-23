package ar.edu.unlam.figuritas.data.api

import ar.edu.unlam.figuritas.model.response.CountryResponse
import ar.edu.unlam.figuritas.domain.response.PlayerResponse
import ar.edu.unlam.figuritas.domain.response.TeamResponse
import retrofit2.Response
import javax.inject.Inject

class PlayerClient @Inject constructor(
    private val servicePlayerApi: PlayerAPI
) {
    suspend fun searchPlayerById(playerId: Int): Response<PlayerResponse> {
        return servicePlayerApi.searchPlayerById(playerId)
    }

    suspend fun searchPlayersByCountryId(countryId: Int): Response<TeamResponse?> {
        return servicePlayerApi.searchPlayersByCountryId(countryId)
    }

    suspend fun searchCountryById(countryId: Int) : Response<CountryResponse>{
        return servicePlayerApi.searchCountryById(countryId)
    }
}