package ar.edu.unlam.figuritas.data.api

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock


internal class PlayerClientTest{

    @MockK
    private lateinit var apiClient: PlayerClient

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        apiClient = PlayerClient()
    }

    @Test
    fun siBuscoUnJugadorMeLoTrae() = runBlocking{

        var idJugador = 184798

        var response = apiClient.searchPlayerById(idJugador).body()

        if (response != null){
            assert(response.name == "Lionel Messi")
        }

    }

    @Test
    fun siBuscoUnEquipoMeLoTrae() = runBlocking {

        var equipoNombre = "Manchester United"

        var response = apiClient.searchTeamByName(equipoNombre).body()

        if (response != null) {
            assert(response.data[0].shortName == "MUN")
            assert(response.data[0].id == 14)
        }
    }

    @Test
    fun siBuscoUnaPlantillaMeLaTrae() = runBlocking {

        var teamId = 14

        var response = apiClient.searchSquadByTeamId(teamId).body()

        if (response != null) {
            assert(response.data[0].dataPlayer.id == 455376)
        }
    }
}