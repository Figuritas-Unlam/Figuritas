package ar.edu.unlam.figuritas.data.api

import ar.edu.unlam.figuritas.model.response.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlayerClient {

    private val servicePlayerApi : PlayerAPI = Retrofit.Builder()
        .baseUrl("https://soccer.sportmonks.com/api/v2.0/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PlayerAPI::class.java)

    suspend fun searchPlayerById(playerId : Int) : Response<PlayerResponse> {
        return servicePlayerApi.searchPlayerById(playerId)
    }

    suspend fun searchPlayerByCountryId(countryId : Int) : Response<TeamResponse> {
        return servicePlayerApi.searchPlayersByCountryId(countryId)
    }
}