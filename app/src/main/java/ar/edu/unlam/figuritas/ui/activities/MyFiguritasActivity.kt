package ar.edu.unlam.figuritas.ui.activities

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Vibrator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.figuritas.R
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import ar.edu.unlam.figuritas.ui.OpenPackViewModel
import ar.edu.unlam.figuritas.ui.activities.ui.theme.FiguritasTheme
import ar.edu.unlam.figuritas.ui.activities.ui.theme.Orange
import ar.edu.unlam.figuritas.ui.activities.ui.theme.RedQatar
import ar.edu.unlam.figuritas.ui.viewModel.FiguritasViewModel
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFiguritasActivity : ComponentActivity(), SensorEventListener {
    private val viewModel: OpenPackViewModel by viewModels()
    private val viewModelos: FiguritasViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            viewModel.setLists()
            FiguritasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        BackgroundActivity(viewModel)
                    }
                }
            }
        }
        setShakeSensor()
    }

    //Sense Shake
    private fun setShakeSensor() {
        viewModelos.sensorManager= getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onSensorChanged(srEvent: SensorEvent?) {
        if(srEvent!=null && srEvent.sensor.type == Sensor.TYPE_ACCELEROMETER){
            startOpenPack(srEvent)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    private fun startOpenPack(event: SensorEvent) {
        val xVal = event.values[0]
        val yVal = event.values[1]
        val zVal = event.values[2]
        val accelerationSquareRoot = (xVal * xVal + yVal * yVal + zVal * zVal) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)
        if (accelerationSquareRoot >= 12) {
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(300)
            val openPackActivityIntent = Intent(baseContext, OpenPackActivity::class.java)
            startActivity(openPackActivityIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModelos.sensorManager.registerListener(this,
            viewModelos.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        viewModelos.sensorManager.unregisterListener(this)
        viewModel.playerRepetidos.clear()
        viewModel.playerNuevas.clear()
    }

}

@Composable
fun BackgroundActivity(viewModel: OpenPackViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(1f)
            .paint(
                painterResource(id = R.drawable.background_qatar),
                contentScale = ContentScale.FillHeight
            )
    ) {
        MisFiguritas()
        FiguritaNuevasTxt()
        RvNuevas(viewModel)
        ParaIntercambiar()
        RvRepetidas(viewModel)


    }
}

@Composable
fun MisFiguritas() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Mis Figuritas",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
    }
}

@Composable
fun FiguritaNuevasTxt() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.figuritas_nuevas),
            contentDescription = "hola",
            Modifier
                .width(200.dp)
                .height(50.dp),
            alpha = 0.8f
        )
    }
}

@Composable
fun RvNuevas(viewModel: OpenPackViewModel) {
    //val rememberPlayers = remember { viewModel.playerList }
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(8.dp)) {
        items(viewModel.playerNuevas) { player ->
            player?.let { FiguritasNuevas(player = it) }

        }
    }
}



@Composable
fun FiguritasNuevas(player: PlayerEntity) {
    Card(
        border = BorderStroke(2.dp, Color.Yellow),
        modifier = Modifier
            .width(140.dp)
            .height(260.dp)
            .padding(top = 40.dp),
        backgroundColor = Orange,

        elevation = 50.dp
    ) {

        Column(
            Modifier
                .width(140.dp)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp), horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = painterResource(id = R.drawable.qatar_icon),
                    contentDescription = "bandera",
                    modifier = Modifier
                        .width(50.dp)
                        .height(40.dp)
                )
                AsyncImage(
                    model = player.imageCountry,
                    contentDescription = "bandera",
                    modifier = Modifier
                        .width(50.dp)
                        .height(40.dp)

                )

            }

            AsyncImage(
                model = player.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .width(140.dp)
                    .height(140.dp)
                    .padding(top = 5.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .height(20.dp)
                    .background(Color.White)


            ) {
                Text(
                    text = player.playerName, modifier = Modifier.align(Alignment.Center),
                    color = RedQatar,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    softWrap = false,
                    lineHeight = TextUnit.Unspecified

                )
            }

        }

    }
}

@Composable
fun ParaIntercambiar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.repetidas),
            contentDescription = "hola",
            Modifier
                .width(200.dp)
                .height(60.dp),
            alpha = 0.8f
        )
    }
}

@Composable
fun FiguritasRepetidas(player: PlayerEntity) {
    Card(
        border = BorderStroke(2.dp, Color.Yellow),
        modifier = Modifier
            .width(140.dp)
            .height(260.dp)
            .padding(top = 40.dp),
        backgroundColor = Orange,

        elevation = 50.dp
    ) {

        Column(
            Modifier
                .width(140.dp)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.qatar_icon),
                    contentDescription = "bandera",
                    modifier = Modifier
                        .padding(start = 1.dp)
                        .width(50.dp)
                        .height(40.dp)
                )
                AsyncImage(
                    model = player.imageCountry,
                    contentDescription = "bandera",
                    modifier = Modifier
                        .width(50.dp)
                        .height(40.dp)

                )


            }


            AsyncImage(
                model = player.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .width(140.dp)
                    .height(140.dp)
                    .padding(top = 5.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .height(20.dp)
                    .background(Color.White)


            ) {
                Text(
                    text = player.playerName, modifier = Modifier.align(Alignment.Center),
                    color = RedQatar,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    softWrap = false,
                    lineHeight = TextUnit.Unspecified

                )

            }

        }

    }
}

@Composable
fun RvRepetidas(viewModel: OpenPackViewModel) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(8.dp)) {
        items(viewModel.playerRepetidos) { player ->
            player?.let { FiguritasRepetidas(player = it) }

        }
    }
}