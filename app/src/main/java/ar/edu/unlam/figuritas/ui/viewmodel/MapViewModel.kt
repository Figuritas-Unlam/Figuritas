package ar.edu.unlam.figuritas.ui.viewmodel

import android.annotation.SuppressLint
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.figuritas.Domain.PolyLineRouteProvider
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapViewModel(private val routeProvider: PolyLineRouteProvider): ViewModel() {
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

    fun getPolyline(start: String, end: String): PolylineOptions? {
        var polyLine: PolylineOptions? = null
        viewModelScope.launch {
            //Esta llamada pasaria al viewModel
            polyLine = routeProvider.getPolyline(start, end)
        }
        return polyLine
    }

    fun resetLocations() {
        name = ""
        poly?.remove()
        poly = null
    }
}
