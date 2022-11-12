package ar.edu.unlam.figuritas.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import ar.edu.unlam.figuritas.model.response.MockPlayerProvisorio
import ar.edu.unlam.figuritas.ui.activities.ui.theme.FiguritasTheme
import ar.edu.unlam.figuritas.ui.activities.ui.theme.Orange
import ar.edu.unlam.figuritas.ui.activities.ui.theme.RedQatar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFiguritasActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiguritasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        BackgroundActivity()
                    }
                }
            }
        }
    }

}


@Composable
fun BackgroundActivity() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(1f)
            .paint(painterResource(id = R.drawable.background_qatar), contentScale = ContentScale.FillHeight)
    ) {
        MisFiguritas()
        FiguritaNuevasTxt()
        RvNuevas()
        ParaIntercambiar()
        RvRepetidas()


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
fun RvNuevas() {

    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(8.dp)) {
        items(getMessiMock()) { messiMock ->
            FiguritasNuevas(messiMock = messiMock)

        }
    }
}

fun getMessiMock(): List<MockPlayerProvisorio> {
    return listOf(
        MockPlayerProvisorio("Lionel Messi", R.drawable.messi),
        MockPlayerProvisorio("Lionel Messi", R.drawable.messi),
        MockPlayerProvisorio("Lionel Messi", R.drawable.messi),
        MockPlayerProvisorio("Lionel Messi", R.drawable.messi),
        MockPlayerProvisorio("Lionel Messi", R.drawable.messi),
        MockPlayerProvisorio("Lionel Messi", R.drawable.messi),
        MockPlayerProvisorio("Lionel Messi", R.drawable.messi),
        MockPlayerProvisorio("Lionel Messi", R.drawable.messi),
        MockPlayerProvisorio("Lionel Messi", R.drawable.messi),
        MockPlayerProvisorio("Lionel Messi", R.drawable.messi),

        )
}


@Composable
fun FiguritasNuevas(messiMock: MockPlayerProvisorio) {
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
                Image(
                    painter = painterResource(id = R.drawable.arg),
                    contentDescription = "bandera",
                    modifier = Modifier
                        .width(50.dp)
                        .height(40.dp)

                )

            }


            Image(
                painter = painterResource(id = messiMock.image),
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
                    text = messiMock.name, modifier = Modifier.align(Alignment.Center),
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
fun FiguritasRepetidas(messiMock: MockPlayerProvisorio) {
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
                Image(
                    painter = painterResource(id = R.drawable.arg),
                    contentDescription = "bandera",
                    modifier = Modifier
                        .width(50.dp)
                        .height(40.dp)

                )

            }


            Image(
                painter = painterResource(id = messiMock.image),
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
                    text = messiMock.name, modifier = Modifier.align(Alignment.Center),
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
fun RvRepetidas() {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(8.dp)) {
        items(getMessiMock()) { messiMock ->
            FiguritasRepetidas(messiMock = messiMock)

        }
    }
}

