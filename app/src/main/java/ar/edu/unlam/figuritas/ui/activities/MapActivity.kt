package ar.edu.unlam.figuritas.ui.activities

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ar.edu.unlam.figuritas.R
import ar.edu.unlam.figuritas.Data.api.OpenRouteClient
import ar.edu.unlam.figuritas.Domain.Models.MeetPoints
import ar.edu.unlam.figuritas.Domain.PolyLineRouteProvider
import ar.edu.unlam.figuritas.databinding.ActivityMapBinding
import ar.edu.unlam.figuritas.ui.viewmodel.MapViewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

//Se tiene que inyectar VM y al que se le inyectaría routeProvider
class MapActivity() : AppCompatActivity(),
    OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener {

    private lateinit var mapViewModel: MapViewModel
    private val LOCATION_REQUEST = 0
    private val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    private lateinit var binding: ActivityMapBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        suscribeToViewModel()
        createMap()
        setManagerLocation()
    }

    private fun suscribeToViewModel() {
        val client = OpenRouteClient()
        val polyProvider=PolyLineRouteProvider(client)
        mapViewModel= MapViewModel(polyProvider)
    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        this,
        FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, FINE_LOCATION)) {
            //Tosast("Acceptarlos en ajustes")
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(FINE_LOCATION), LOCATION_REQUEST)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_REQUEST -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //this.map.isMyLocationEnabled=true
            } else {
                //toast("activar location desde permisos")
            }
            else -> {}
        }
    }

    private fun createMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.map = googleMap
        enableLocation()
        if (!map.isMyLocationEnabled) {
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(-34.606252, -58.435666),
                    11.55F
                )
            )
        }
        map.setOnMyLocationButtonClickListener(this)
        map.setOnMapClickListener {
            mapViewModel.resetLocations()
            mapViewModel.poly?.remove()
        }
        mapViewModel.setActualLocation(isLocationPermissionGranted())
        setMarkersMeetPoints()
    }

    override fun onMyLocationButtonClick(): Boolean {
        return false
    }

    private fun enableLocation() {
        if (isLocationPermissionGranted()) {
            //this.map.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    private fun setMarkersMeetPoints() {
        map.addMarker(MarkerOptions().title(MeetPoints.POINT_ONE.namePoint).position(MeetPoints.POINT_ONE.coordinates))
        map.addMarker(MarkerOptions().title(MeetPoints.POINT_TWO.namePoint).position(MeetPoints.POINT_TWO.coordinates))
        map.addMarker(MarkerOptions().title(MeetPoints.POINT_THREE.namePoint).position(MeetPoints.POINT_THREE.coordinates))
        map.addMarker(MarkerOptions().title(MeetPoints.POINT_FOUR.namePoint).position(MeetPoints.POINT_FOUR.coordinates))
        map.addMarker(MarkerOptions().title(MeetPoints.POINT_FIVE.namePoint).position(MeetPoints.POINT_FIVE.coordinates))
        map.addMarker(MarkerOptions().title(MeetPoints.POINT_SIX.namePoint).position(MeetPoints.POINT_SIX.coordinates))
        setMarkerListener()
    }

    private fun setMarkerListener() {
        map.setOnMarkerClickListener {
            val coordinates = it.position
            if (isLocationPermissionGranted()) {
                if (mapViewModel.name != it.title.toString()) {
                    mapViewModel.resetLocations()
                    mapViewModel.name = it.title.toString()
                    drawPolyLineRoute(
                        "${mapViewModel.actualLocation?.longitude}, ${mapViewModel.actualLocation?.latitude}",
                        "${coordinates.longitude}, ${coordinates.latitude}"
                    )
                }
            } else {
                //Toast("No tiene los permisos de ubicacion activados
            }
            return@setOnMarkerClickListener true
        }
    }

    private fun drawPolyLineRoute(start: String, end: String) {
        val polyLine = mapViewModel.getPolyline(start, end)
        if (polyLine != null) {
            runOnUiThread {
                mapViewModel.poly = map.addPolyline(polyLine)
            }
        } else {
            //Mensaje "No se pudo calcular la ruta"
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::map.isInitialized) return
        mapViewModel.setActualLocation(isLocationPermissionGranted())
    }

    private fun setManagerLocation() {
        mapViewModel.fusedLocation = LocationServices.getFusedLocationProviderClient(this)
    }
}