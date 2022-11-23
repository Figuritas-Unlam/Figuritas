package ar.edu.unlam.figuritas.data

import android.util.Log
import ar.edu.unlam.figuritas.data.OpenRouteService.Companion.API_KEY_ROUTE
import ar.edu.unlam.figuritas.domain.response.RouteResponse
import ar.edu.unlam.figuritas.domain.response.mapToRoute
import ar.edu.unlam.figuritas.domain.model.Route
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class OpenRouteClient @Inject constructor(
    private val openRouteService: OpenRouteService
){
    suspend fun getRoute(start: String, end: String): List<List<Double>> {
        var route: List<List<Double>> = listOf()
        try {
            val response = openRouteService.getRoute(
                key=API_KEY_ROUTE,
                start = start,
                end= end
            )
            val rta=response.body()
            if (response.isSuccessful && rta!= null) {
                Log.e("Points", "${rta.features.first().geometry.coordinates.component5()}")
                route = rta.features.first().geometry.coordinates
            }else Log.e("rtaNull", "kk")
        }catch (e: Exception){
            Log.e("notAnda", "${e.message}")
        }
        return route
    }
}

interface OpenRouteService {

    companion object{
        const val API_KEY_ROUTE = "5b3ce3597851110001cf6248349f0f0bc0aa4a07a3c239a5f3611c6f"
    }

    @GET("/v2/directions/driving-car")
    suspend fun getRoute(
        @Query("api_key") key: String,
        @Query("start", encoded = true) start: String,
        @Query("end", encoded = true) end: String
    ): Response<RouteResponse>
}