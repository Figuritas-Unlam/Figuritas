package ar.edu.unlam.figuritas.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ar.edu.unlam.figuritas.R
import ar.edu.unlam.figuritas.Data.api.OpenRouteClient
import ar.edu.unlam.figuritas.Domain.Models.MeetPoint
import ar.edu.unlam.figuritas.Domain.PolyLineRouteProvider
import ar.edu.unlam.figuritas.databinding.ActivityMapBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Se tiene que inyectar VM y al que se le inyectaría routeProvider
class MapActivity() : AppCompatActivity(),
    OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener {

    //private val viewModel: MainViewModel by viewModels { MainViewModel.Factory() }
    private lateinit var routeProvider : PolyLineRouteProvider
    private val LOCATION_REQUEST = 0
    private val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    private lateinit var binding: ActivityMapBinding
    private lateinit var map: GoogleMap

    //Deberia de ser una lista obtenida de una BD o similar
    val markers = listOf(
        MeetPoint("PointOne", LatLng(-34.601732, -58.469339)),
        MeetPoint("PointTwo", LatLng(-34.587871, -58.463758)),
        MeetPoint("PointThree", LatLng(-34.587862, -58.395270)),
        MeetPoint("PointFour", LatLng(-34.614463, -58.404880)),
        MeetPoint("PointFive", LatLng(-34.641201, -58.479896)),
        MeetPoint("PointSix", LatLng(-34.560833, -58.492334))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createMap()
        //Inyectado en VM
        routeProvider = PolyLineRouteProvider(OpenRouteClient())
        setManagerLocation()
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
            resetLocations()
            poly?.remove()
        }
        setActualLocation(isLocationPermissionGranted())
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
        markers.forEach {
            map.addMarker(MarkerOptions().title(it.namePoint).position(it.coordinates))
        }
        setMarkerListener()
    }

    private fun setMarkerListener() {
        map.setOnMarkerClickListener {
            val coordinates = it.position
            if (isLocationPermissionGranted()) {
                if (name != it.title.toString()) {
                    resetLocations()
                    name = it.title.toString()
                    drawPolyLineRoute(
                        "-58.455490, -34.610831",
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
        val polyLine = getPolyline(start, end)
        if (polyLine != null) {
            Toast.makeText(this, "poly lat", Toast.LENGTH_SHORT).show()
            runOnUiThread {
                poly = map.addPolyline(polyLine)
            }
        } else {
            //Mensaje "No se pudo calcular la ruta"
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::map.isInitialized) return
        setActualLocation(isLocationPermissionGranted())
    }

    //Estar en y ser manejadas por el viewModel
    //Se podria dar un mejor manejo mediante LiveData<> y observer
    private var name = ""
    var poly: Polyline? = null
    private lateinit var fusedLocation: FusedLocationProviderClient
    private var actualLocation: LatLng? = null

    //En el INIT{} del VM
    private fun setManagerLocation() {
        fusedLocation = LocationServices.getFusedLocationProviderClient(this)
    }

    //1,2, 3, actualLocation y fusedLocation deberian pasar al VM
    //1
    @SuppressLint("MissingPermission")
    private fun setActualLocation(isPermissionLocationGranted: Boolean) {
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

    //2
    private val solicitudDeDatosDeGPS = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    //3
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

    //en el VM
    private fun getPolyline(start: String, end: String): PolylineOptions? {
        var polyLine: PolylineOptions? = null
        CoroutineScope(Dispatchers.IO).launch {
            //Esta llamada pasaria al viewModel
            polyLine = routeProvider.getPolyline(start, end)
        }
        return polyLine
    }

    //Pasaria al viewModel
    private fun resetLocations() {
        name = ""
        poly?.remove()
        poly = null
    }
}