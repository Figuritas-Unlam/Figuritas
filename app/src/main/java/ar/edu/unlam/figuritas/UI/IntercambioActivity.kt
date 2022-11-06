package ar.edu.unlam.figuritas.UI

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothProfile
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ar.edu.unlam.figuritas.databinding.ActivityIntercambioBinding

class IntercambioActivity : AppCompatActivity() {
    private val BT = Manifest.permission.BLUETOOTH
    private val BT_REQUEST = 2
    private val REQUEST_ENABLE_BT=1
    private lateinit var binding: ActivityIntercambioBinding
    private lateinit var bluetoothAdapter: BluetoothAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityIntercambioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBlueToothAdapter()
        /*if(!isBluetoothPermissionGranted()){
            requestBluetoothPermission()
        }*/

    }
    private fun isBluetoothPermissionGranted() = ContextCompat.checkSelfPermission(
        this,
        BT
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestBluetoothPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, BT)) {
            //Tosast("Acceptarlos en ajustes")
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(BT), BT_REQUEST)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            BT_REQUEST->if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                //FUNCIONALIDAD
                validationIsBTEnable()
            }else {
                //toast("activar bt desde permisos")
            }
            else->{}
        }
    }

    private fun validationIsBTEnable() {
        if (bluetoothAdapter?.isEnabled == false) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }
    }

    // Get the default adapter
    private fun setBlueToothAdapter() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK){

        }
    }
    val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
    pairedDevices?.forEach { device ->
        when{
            binding.h1.text==null-> binding.h1.text=device.
                binding.h2.text==null->
            binding.h3.text==null->
        }
    }

}