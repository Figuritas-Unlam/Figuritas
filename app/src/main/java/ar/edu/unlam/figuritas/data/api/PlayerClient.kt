package ar.edu.unlam.figuritas.data.api

import ar.edu.unlam.figuritas.model.response.PlayerResponse
import ar.edu.unlam.figuritas.model.response.TeamResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class PlayerClient @Inject constructor(
    private val servicePlayerApi: PlayerAPI
) {
    suspend fun searchPlayerById(playerId: Int): Response<PlayerResponse> {
        return servicePlayerApi.searchPlayerById(playerId)
    }

    suspend fun searchPlayersByCountryId(countryId: Int): Response<TeamResponse> {
        return servicePlayerApi.searchPlayersByCountryId(countryId)
    }
}