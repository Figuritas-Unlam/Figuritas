package ar.edu.unlam.figuritas.data.api

import ar.edu.unlam.figuritas.model.response.PlayerResponse
import ar.edu.unlam.figuritas.model.response.PlayerResponseData
import ar.edu.unlam.figuritas.model.response.Position
import ar.edu.unlam.figuritas.model.response.PositionData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response


internal class PlayerClientTest{

    @MockK
    private lateinit var apiClient: PlayerClient

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        coEvery { apiClient.searchPlayerById(any()) } returns Response.success(
            PlayerResponse(
                PlayerResponseData(
                    184798,
                    0,
                    0,
                    "Lionel Messi",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    PositionData(Position(0, ""))
                )
            )
        )
    }

    @Test
    fun siBuscoUnJugadorMeLoTrae() = runBlocking{

        val idJugador = 184798

        val response = apiClient.searchPlayerById(idJugador).body()

        if (response != null){
            assert(response.data.name == "Lionel Messi")
        }

    }

//    @Test
//    fun siBuscoUnEquipoMeLoTrae() = runBlocking {
//
//        var equipoNombre = "Manchester United"
//
//        var response = apiClient.searchTeamByName(equipoNombre).body()
//
//        if (response != null) {
//            assert(response.data[0].shortName == "MUN")
//            assert(response.data[0].id == 14)
//        }
//    }
//
//    @Test
//    fun siBuscoUnaPlantillaMeLaTrae() = runBlocking {
//
//        var teamId = 14
//
//        var response = apiClient.searchSquadByTeamId(teamId).body()
//
//        if (response != null) {
//            assert(response.data[0].dataPlayer.id == 455376)
//        }
//    }
}