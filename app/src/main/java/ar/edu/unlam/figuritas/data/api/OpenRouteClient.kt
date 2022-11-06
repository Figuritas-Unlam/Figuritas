package ar.edu.unlam.figuritas.Data.api

import ar.edu.unlam.figuritas.Data.models.RouteResponse
import ar.edu.unlam.figuritas.Data.models.mapToRoute
import ar.edu.unlam.figuritas.Domain.Models.Route
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class OpenRouteClient{

    private val openRouteService: OpenRouteService = Retrofit.Builder()
        .baseUrl("https://api.openrouteservice.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OpenRouteService::class.java)

    suspend fun getRoute(start: String, end: String): Route? {
        var route: Route? = null
        try {
            val response = openRouteService.getRoute(
                "5b3ce3597851110001cf6248349f0f0bc0aa4a07a3c239a5f3611c6f",
                start,
                end
            )
            if (response.isSuccessful && response.body() != null) {
                route = response.body()?.mapToRoute()
            }
        }catch (e: Exception){
            //Log con la excepcion
        }
        return route
    }
}

interface OpenRouteService {
    @GET("/v2/directions/driving-car")
    suspend fun getRoute(
        @Query("api_key") key: String,
        @Query("start", encoded = true) start: String,
        @Query("end", encoded = true) end: String): Response<RouteResponse>
}