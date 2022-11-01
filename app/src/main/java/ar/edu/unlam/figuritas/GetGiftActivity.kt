package ar.edu.unlam.figuritas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ar.edu.unlam.figuritas.databinding.ActivityGetGiftBinding
import com.google.zxing.integration.android.IntentIntegrator


class CameraActivity : AppCompatActivity() {

    //qrManager se debe inyectar al ViewModel
    private val qrManager = QRManager()
    private lateinit var binding: ActivityGetGiftBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetGiftBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnScann.setOnClickListener {
            startCamera()
        }
        setQR()
    }

    private fun startCamera() {
        val integrator=IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanee el QR para obtener el regalo")
        integrator.setOrientationLocked(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result!=null && qrManager.isVerify(result.contents)){
            if(result.contents==null){
                Toast.makeText(this,"No se pudo recuperar la informacion", Toast.LENGTH_SHORT).show()
            }else{
                qrManager.deserializationPruebaFiguritas(result.contents).figus.forEach{
                    val x = binding.txt.text
                    binding.txt.text="$x / ${it.edad} / ${it.name} / ${it.pais}"
                }
                Toast.makeText(this,"Recuperacion correcta de datos", Toast.LENGTH_SHORT).show()
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
            Toast.makeText(this,"QR vacío", Toast.LENGTH_SHORT).show()
        }
    }

    //De aca para abajo es para probar funcionalidad
    private fun setQR() {
        try {
            binding.image.setImageBitmap(qrManager.generateQR(createSomeFigus(), 450, 450))
        } catch (e: Exception) {
            //No se pudo generar el QR
            Toast.makeText(this,"Error al generar QR", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createSomeFigus(): PruebaRegaloFiguritas{
        val list = mutableListOf<PruebaFiguritas>()
        for (i in 1..3){
            list.add(
                PruebaFiguritas(
                    "babbab",
                    321321,
                    "abaaba"
                )
            )
        }
        return PruebaRegaloFiguritas(list)
    }
}
data class PruebaFiguritas(val name: String, val edad: Int, val pais: String)
data class PruebaRegaloFiguritas(val figus : List<PruebaFiguritas>)
