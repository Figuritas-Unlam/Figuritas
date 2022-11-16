package ar.edu.unlam.figuritas.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import ar.edu.unlam.figuritas.databinding.ActivitySwapsBinding
import ar.edu.unlam.figuritas.model.PruebaFiguritas
import ar.edu.unlam.figuritas.model.PruebaRegaloFiguritas
import ar.edu.unlam.figuritas.model.QRManager
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import ar.edu.unlam.figuritas.model.response.PlayerResponseData
import ar.edu.unlam.figuritas.model.response.Position
import ar.edu.unlam.figuritas.model.response.PositionData
import ar.edu.unlam.figuritas.ui.adapter.SwapsStickersAdapter
import com.google.zxing.integration.android.IntentIntegrator


class SwapsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySwapsBinding
    private lateinit var qrManager : QRManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwapsBinding.inflate(layoutInflater)
        val view = binding.root

        binding.scanCode.setOnClickListener {
            startCamera()
        }

        binding.swapsButton.setOnClickListener {
            setQR()
        }

        val recyclerView = binding.recyclerView
        recyclerView.adapter = getStickersAdapter()
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            DividerItemDecoration.HORIZONTAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

        setContentView(view)
    }

    private fun startCamera() {
        val integrator= IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanee el QR para obtener el regalo")
//        integrator.setOrientationLocked(true)
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
                    //Se agruegen a mis figuri (O se obtengan como un paquete)
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

    private fun createSomeFigus(): PruebaRegaloFiguritas {
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

    private fun getStickersAdapter(): SwapsStickersAdapter {
        return SwapsStickersAdapter(arrayOf(
            PlayerResponse(data=PlayerResponseData(playerId=254491, teamId=2626, countryId=458, name="Uriel Antuna", fullname="Carlos Uriel Antuna Romero", height="174 cm", weight="59 kg", birthdate="21/08/1997", nationality="Mexico", image="https://cdn.sportmonks.com/images/soccer/players/27/254491.png", position=PositionData(Position(0, "")))),
            PlayerResponse(data=PlayerResponseData(playerId=26955, teamId=42, countryId=556, name="Wout Faes", fullname="Wout Faes", height="187 cm", weight="84 kg", birthdate="03/04/1998", nationality="Belgium", image="https://cdn.sportmonks.com/images//soccer/players/11/26955.png", position=PositionData(Position(0, "")))),
            PlayerResponse(data=PlayerResponseData(playerId=302604, teamId=16184, countryId=35376, name="Nawaf Al Abid", fullname="Nawaf Shaker Al Abid", height="170 cm", weight="60 kg", birthdate="26/01/1990", nationality="Saudi Arabia", image="https://cdn.sportmonks.com/images/soccer/players/12/302604.png", position=PositionData(Position(0, "")))),
            PlayerResponse(data=PlayerResponseData(playerId=95896, teamId=686, countryId=17, name="Alexander Djiku", fullname="Alexander Djiku", height="182 cm", weight="74 kg", birthdate="09/08/1994", nationality="Ghana", image="https://cdn.sportmonks.com/images/soccer/players/24/95896.png", position=PositionData(Position(0, "")))),
            PlayerResponse(data=PlayerResponseData(playerId=244293, teamId=10036, countryId=459, name="Romario Ibarra", fullname="Romario Andrés Ibarra Mina", height="174 cm", weight="77 kg", birthdate="24/09/1994", nationality="Ecuador", image="https://cdn.sportmonks.com/images//soccer/players/5/244293.png", position=PositionData(Position(0, "")))),
            PlayerResponse(data=PlayerResponseData(playerId=254491, teamId=2626, countryId=458, name="Uriel Antuna", fullname="Carlos Uriel Antuna Romero", height="174 cm", weight="59 kg", birthdate="21/08/1997", nationality="Mexico", image="https://cdn.sportmonks.com/images/soccer/players/27/254491.png", position=PositionData(Position(0, "")))),
            PlayerResponse(data=PlayerResponseData(playerId=26955, teamId=42, countryId=556, name="Wout Faes", fullname="Wout Faes", height="187 cm", weight="84 kg", birthdate="03/04/1998", nationality="Belgium", image="https://cdn.sportmonks.com/images//soccer/players/11/26955.png", position=PositionData(Position(0, "")))),
            PlayerResponse(data=PlayerResponseData(playerId=302604, teamId=16184, countryId=35376, name="Nawaf Al Abid", fullname="Nawaf Shaker Al Abid", height="170 cm", weight="60 kg", birthdate="26/01/1990", nationality="Saudi Arabia", image="https://cdn.sportmonks.com/images/soccer/players/12/302604.png", position=PositionData(Position(0, "")))),
            PlayerResponse(data=PlayerResponseData(playerId=95896, teamId=686, countryId=17, name="Alexander Djiku", fullname="Alexander Djiku", height="182 cm", weight="74 kg", birthdate="09/08/1994", nationality="Ghana", image="https://cdn.sportmonks.com/images/soccer/players/24/95896.png", position=PositionData(Position(0, "")))),
            PlayerResponse(data=PlayerResponseData(playerId=244293, teamId=10036, countryId=459, name="Romario Ibarra", fullname="Romario Andrés Ibarra Mina", height="174 cm", weight="77 kg", birthdate="24/09/1994", nationality="Ecuador", image="https://cdn.sportmonks.com/images//soccer/players/5/244293.png", position=PositionData(Position(0, "")))),
            PlayerResponse(data=PlayerResponseData(playerId=254491, teamId=2626, countryId=458, name="Uriel Antuna", fullname="Carlos Uriel Antuna Romero", height="174 cm", weight="59 kg", birthdate="21/08/1997", nationality="Mexico", image="https://cdn.sportmonks.com/images/soccer/players/27/254491.png", position=PositionData(Position(0, "")))),
            PlayerResponse(data=PlayerResponseData(playerId=26955, teamId=42, countryId=556, name="Wout Faes", fullname="Wout Faes", height="187 cm", weight="84 kg", birthdate="03/04/1998", nationality="Belgium", image="https://cdn.sportmonks.com/images//soccer/players/11/26955.png", position=PositionData(Position(0, "")))),
            PlayerResponse(data=PlayerResponseData(playerId=302604, teamId=16184, countryId=35376, name="Nawaf Al Abid", fullname="Nawaf Shaker Al Abid", height="170 cm", weight="60 kg", birthdate="26/01/1990", nationality="Saudi Arabia", image="https://cdn.sportmonks.com/images/soccer/players/12/302604.png", position=PositionData(Position(0, "")))),
            PlayerResponse(data=PlayerResponseData(playerId=95896, teamId=686, countryId=17, name="Alexander Djiku", fullname="Alexander Djiku", height="182 cm", weight="74 kg", birthdate="09/08/1994", nationality="Ghana", image="https://cdn.sportmonks.com/images/soccer/players/24/95896.png", position=PositionData(Position(0, "")))),
            PlayerResponse(data=PlayerResponseData(playerId=244293, teamId=10036, countryId=459, name="Romario Ibarra", fullname="Romario Andrés Ibarra Mina", height="174 cm", weight="77 kg", birthdate="24/09/1994", nationality="Ecuador", image="https://cdn.sportmonks.com/images//soccer/players/5/244293.png", position=PositionData(Position(0, ""))))
        ))
    }
}