package ar.edu.unlam.figuritas.domain

import android.util.Log
import ar.edu.unlam.figuritas.data.OpenRouteClient
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import javax.inject.Inject

class PolyLineRouteProvider @Inject constructor(private val openRouteClient: OpenRouteClient){
    suspend fun getPolyline(start: String, end : String): List<List<Double>> {
        val points = openRouteClient.getRoute(start, end)
        //Le seteamos los puntos de la ruta obtenida a Polyline, para que genere el dibujo
        Log.e("ProviderPoint", "/${points.size}")
        return points
    }
}