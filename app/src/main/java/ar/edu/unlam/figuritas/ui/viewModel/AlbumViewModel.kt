package ar.edu.unlam.figuritas.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.figuritas.data.api.PlayerAPI
import ar.edu.unlam.figuritas.data.repository.DatabaseRepository
import ar.edu.unlam.figuritas.data.repository.PlayerRepository
import ar.edu.unlam.figuritas.model.Seleccion
import ar.edu.unlam.figuritas.model.WorldCupTeamId
import ar.edu.unlam.figuritas.model.entities.Player
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import ar.edu.unlam.figuritas.model.response.PlayerResponseData
import ar.edu.unlam.figuritas.model.response.Position
import ar.edu.unlam.figuritas.model.response.PositionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(var databaseRepository: DatabaseRepository, var playerRepository: PlayerRepository) : ViewModel(){

    var listPlayers = mutableListOf<PlayerEntity>()
    var listCountries = mutableListOf<WorldCupTeamId>()
    var listSquads = mutableListOf<Seleccion>()
    private val _seleccionData = MutableLiveData<List<Seleccion?>>()
    val seleccionData: MutableLiveData<List<Seleccion?>> = _seleccionData


    init {
        setCountrys()
        searchSelecciones()
    }

    fun searchPlayer () : List<PlayerEntity>{
        return databaseRepository.getallPlayers()

    }

    fun searchSelecciones(){

        for(seleccion in listCountries){
            searchPlayers(seleccion)
        }
        _seleccionData.value = listSquads
    }

    fun searchPlayers(country : WorldCupTeamId){
        try {
            viewModelScope.launch {
                val response = playerRepository.getPlayersByCountry(country)
                val playersDatabase = databaseRepository.getallPlayers()
                for (player in response!!) {
                    for (playerPaste in playersDatabase) {
                        if (player != null) {
                            listPlayers.add(inAlbum(player, playerPaste))
                        }

                    }
                    val seleccion = Seleccion(country.name, listPlayers)
                    listSquads.add(seleccion)
                    listPlayers.clear()
                }
            }
        }
     catch (e: RuntimeException) {
        e.printStackTrace()
        Log.e("Error fetching players", e.message.toString())
    }
    }

    fun inAlbum(playerResponse : PlayerResponse, playerEntity : PlayerEntity) : PlayerEntity{
        if(playerResponse.data.playerId == playerEntity.playerId){
            return playerEntity
        }
        else{
            return PlayerEntity(
                playerResponse.data.playerId,
                "?",
                "?",
                "?",
                "?",
                0,
                0,
                0,
                false,
                "",
                "",
                ""
            )
        }
    }

    fun setCountrys(){

        listCountries.add(WorldCupTeamId.QATAR)
        listCountries.add(WorldCupTeamId.ECUADOR)
        listCountries.add(WorldCupTeamId.SENEGAL)
        listCountries.add(WorldCupTeamId.NETHERLANDS)

        listCountries.add(WorldCupTeamId.ENGLAND)
        listCountries.add(WorldCupTeamId.IRAN)
        listCountries.add(WorldCupTeamId.UNITED_STATES)
        listCountries.add(WorldCupTeamId.WALES)

        listCountries.add(WorldCupTeamId.ARGENTINA)
        listCountries.add(WorldCupTeamId.SAUDI_ARABIA)
        listCountries.add(WorldCupTeamId.MEXICO)
        listCountries.add(WorldCupTeamId.POLAND)

        listCountries.add(WorldCupTeamId.FRANCE)
        listCountries.add(WorldCupTeamId.AUSTRALIA)
        listCountries.add(WorldCupTeamId.DENMARK)
        listCountries.add(WorldCupTeamId.TUNISIA)

        listCountries.add(WorldCupTeamId.SPAIN)
        listCountries.add(WorldCupTeamId.COSTA_RICA)
        listCountries.add(WorldCupTeamId.GERMANY)
        listCountries.add(WorldCupTeamId.JAPAN)

        listCountries.add(WorldCupTeamId.BELGIUM)
        listCountries.add(WorldCupTeamId.CANADA)
        listCountries.add(WorldCupTeamId.MOROCCO)
        listCountries.add(WorldCupTeamId.CROATIA)

        listCountries.add(WorldCupTeamId.BRAZIL)
        listCountries.add(WorldCupTeamId.SERBIA)
        listCountries.add(WorldCupTeamId.SWITZERLAND)
        listCountries.add(WorldCupTeamId.CAMEROON)

        listCountries.add(WorldCupTeamId.PORTUGAL)
        listCountries.add(WorldCupTeamId.GHANA)
        listCountries.add(WorldCupTeamId.URUGUAY)
        listCountries.add(WorldCupTeamId.KOREA_REPUBLIC)

    }

}