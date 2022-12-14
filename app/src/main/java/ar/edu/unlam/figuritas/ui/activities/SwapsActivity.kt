package ar.edu.unlam.figuritas.ui.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import ar.edu.unlam.figuritas.databinding.ActivitySwapsBinding
import ar.edu.unlam.figuritas.domain.model.PlayerModel
import ar.edu.unlam.figuritas.player_model.QRManager
import ar.edu.unlam.figuritas.ui.adapter.SwapsStickersAdapter
import ar.edu.unlam.figuritas.ui.SwapsViewModel
import com.google.zxing.integration.android.IntentIntegrator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SwapsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySwapsBinding
    private val viewModel: SwapsViewModel by viewModels()
    private val qrManager = QRManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwapsBinding.inflate(layoutInflater)
        val view = binding.root

        setOnClickListeners()
        setRecyclerView()
        setContentView(view)
    }

    private fun setOnClickListeners() {
        binding.scanCode.setOnClickListener {
            startCamera()
        }
        binding.swapsButton.setOnClickListener {
            generateQr()
        }
        binding.openMap.setOnClickListener {
            startMap()
        }
    }

    private fun setRecyclerView() {
        val recyclerView = binding.recyclerView
        suscribeToViewModel()
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            DividerItemDecoration.HORIZONTAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun suscribeToViewModel() {
        viewModel.swappableStickers.observe(this) {
            binding.recyclerView.adapter = SwapsStickersAdapter(it)
            if (it.isNotEmpty()) binding.emptyListText.visibility = View.GONE
        }
    }

    private fun startCamera() {
        val integrator= IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanee el QR para obtener el regalo")
        integrator.initiateScan()
    }

    private fun startMap() {
        val intent = Intent(applicationContext, MapActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null) {
            if(result.contents==null){
                Toast.makeText(this,"No se pudo recuperar la informacion", Toast.LENGTH_SHORT).show()
            } else if(qrManager.isVerified(result.contents)) {
                viewModel.addStickers(qrManager.deserializeStickers(result.contents))
                Toast.makeText(this,"Las figuritas se recibieron con ??xito", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
            Toast.makeText(this,"El c??digo no es v??lido", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateQr() {
        try {
            val iv = ImageView(this)
            val selectedPlayers = getSelectedPlayers()
            if (selectedPlayers.isEmpty()) {
                Toast.makeText(this,"Seleccion?? al menos una figurita", Toast.LENGTH_SHORT).show()
            } else {
                iv.setImageBitmap(qrManager.generateQR(getSelectedPlayers(), 900, 900))
                AlertDialog.Builder(this)
                    .setMessage("Mostr?? el codigo para enviarlas")
                    .setView(iv)
                    .show()
            }
        } catch (e: RuntimeException) {
            //No se pudo generar el QR
            Toast.makeText(this,"Error al generar QR", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSelectedPlayers(): List<PlayerModel> {
        return viewModel.getSelectedStickers()
    }

}