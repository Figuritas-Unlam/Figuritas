package ar.edu.unlam.figuritas.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import ar.edu.unlam.figuritas.databinding.ActivitySwapsBinding
import ar.edu.unlam.figuritas.model.QRManager
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
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

        binding.scanCode.setOnClickListener {
            startCamera()
        }

        binding.swapsButton.setOnClickListener {
            generateQr()
        }

        val recyclerView = binding.recyclerView
        suscribeToViewModel()
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            DividerItemDecoration.HORIZONTAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

        setContentView(view)
    }

    private fun suscribeToViewModel() {
        viewModel.swappableStickers.observe(this) {
            binding.recyclerView.adapter = SwapsStickersAdapter(it)
        }
    }

    private fun startCamera() {
        val integrator= IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanee el QR para obtener el regalo")
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null) {
            if(result.contents==null){
                Toast.makeText(this,"No se pudo recuperar la informacion", Toast.LENGTH_SHORT).show()
            } else if(qrManager.isVerified(result.contents)) {
                viewModel.addStickers(qrManager.deserializeStickers(result.contents))
                Toast.makeText(this,"Las figuritas se recibieron con éxito", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
            Toast.makeText(this,"El código no es válido", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateQr() {
        try {
            // TODO("Show dialog with QR code")
            qrManager.generateQR(createSomeFigus(), 450, 450)
        } catch (e: Exception) {
            //No se pudo generar el QR
            Toast.makeText(this,"Error al generar QR", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createSomeFigus(): List<PlayerEntity> {
        // TODO("return selected stickers for QR code generation")
        return emptyList()
    }

}