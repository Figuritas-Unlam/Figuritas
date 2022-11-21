package ar.edu.unlam.figuritas.Domain

import ar.edu.unlam.figuritas.Data.api.OpenRouteClient
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class PolyLineRouteProvider @Inject constructor(private val openRouteClient: OpenRouteClient){
    suspend fun getPolyline(start: String, end : String): PolylineOptions? {
        val route = openRouteClient.getRoute(start, end)
        var polyLine: PolylineOptions? = null
        if (route!=null) {
            val polyLineOption = PolylineOptions()
            //Le seteamos los puntos de la ruta obtenida a Polyline, para que genere el dibujo
            route.coordinates.forEach {
                polyLineOption.add(LatLng(it[1], it[0]))
            }
            polyLine = polyLineOption
        }
        return polyLine
    }
}