package ar.edu.unlam.figuritas.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.figuritas.data.repository.DatabaseRepository
import ar.edu.unlam.figuritas.data.repository.PlayerRepository
import ar.edu.unlam.figuritas.model.Seleccion
import ar.edu.unlam.figuritas.model.WorldCupTeamId
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import ar.edu.unlam.figuritas.model.response.PlayerResponseData
import ar.edu.unlam.figuritas.model.response.Position
import ar.edu.unlam.figuritas.model.response.PositionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(var databaseRepository: DatabaseRepository, var playerRepository: PlayerRepository) : ViewModel(){

    var listPlayers = mutableListOf<PlayerResponseData>()
    var listCountries = mutableListOf<WorldCupTeamId>()
    var listSquads = mutableListOf<Seleccion>()
    var listSquadsLiveData = MutableLiveData<MutableList<Seleccion>>()
    var playersLiveData = MutableLiveData<MutableList<PlayerResponse>>()



    fun getSquads(): MutableLiveData<MutableList<Seleccion>>{
        return this.listSquadsLiveData
    }

    fun searchPlayers() : MutableList<Seleccion>{
        setCountrys()
        for(countryTeam in listCountries) {
            insertPlayers2(countryTeam)
        }
        return listSquads
    }

    fun insertPlayers2(team : WorldCupTeamId){
        var numero = 26
        while(numero > 0)
        {
            numero--
            listPlayers.add(
                PlayerResponseData(1, 2,3,"?",
                    "?", "?", "?", "?", "?",
                    "?", PositionData(Position(1, "?"))
                )
            )
        }
        val seleccion = Seleccion(team.name, listPlayers)
        listSquads.add(seleccion)
    }
/*
    fun insertPlayers(team : WorldCupTeamId)  {

        val seleccion = Seleccion(team.name, listPlayers)
        viewModelScope.launch {

            listPlayers.clear()
            val response = playerRepository.getPlayersByCountry(team)
            if (response != null) {
                for(player in response){

                    if (player != null) {
                        seleccion.players.add(player.data)
                    }
                }
            }
            listSquadsLiveData.value?.add(seleccion)
        }

    }
*/
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