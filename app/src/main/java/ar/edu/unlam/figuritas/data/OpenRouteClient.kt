package ar.edu.unlam.figuritas.data

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
    suspend fun getRoute(start: String, end: String): Route? {
        var route: Route? = null
        try {
            val response = openRouteService.getRoute(
                start = start,
                end= end
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

    companion object{
        const val API_KEY_ROUTE = "3puVV9TkLHqxgI5xrd00AOS16Zxs2IdcSJLhGciUdRuzswDOvN4KuUVtZuHo"
    }

    @GET("/v2/directions/driving-car?")
    suspend fun getRoute(
        @Query("api_key") key: String = API_KEY_ROUTE,
        @Query("start", encoded = true) start: String,
        @Query("end", encoded = true) end: String): Response<RouteResponse>
}