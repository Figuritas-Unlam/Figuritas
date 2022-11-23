package ar.edu.unlam.figuritas.ui

import android.os.Looper
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.figuritas.domain.PolyLineRouteProvider
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val routeProvider: PolyLineRouteProvider
    ): ViewModel() {
    var name = ""
    var poly: Polyline? = null
    lateinit var fusedLocation: FusedLocationProviderClient
    var actualLocation: LatLng? = null


    fun setActualLocation(isPermissionLocationGranted: Boolean) {
        if (isPermissionLocationGranted) {
            fusedLocation.requestLocationUpdates(
                solicitudDeDatosDeGPS,
                receptorDeUbicaciones,
                Looper.getMainLooper()
            )
        }else{
            actualLocation=null
        }
    }

    private val solicitudDeDatosDeGPS = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private val receptorDeUbicaciones = object : LocationCallback() {
        override fun onLocationResult(location: LocationResult) {
            if (location.lastLocation != null) {
                actualLocation = LatLng(
                    location.lastLocation!!.latitude,
                    location.lastLocation!!.longitude
                )
            }
        }
    }

    suspend fun getPolyline(start: String, end: String): MutableList<List<Double>> {
        val points = mutableListOf<List<Double>>()
        viewModelScope.launch {
            //Esta llamada pasaria al viewModel
            routeProvider.getPolyline(start, end).forEach {
                points.add(it)
            }
        }
        Log.e("PointVM, YAPOLY", "${points.size}")
        return points
    }

    fun resetLocations() {
        name = ""
        poly?.remove()
        poly = null
    }
}
