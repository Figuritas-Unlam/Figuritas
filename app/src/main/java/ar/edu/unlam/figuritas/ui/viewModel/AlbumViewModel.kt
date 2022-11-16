package ar.edu.unlam.figuritas.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.figuritas.data.repository.PlayerRepository
import ar.edu.unlam.figuritas.model.Seleccion
import ar.edu.unlam.figuritas.model.WorldCupTeamId
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(var playerRepository: PlayerRepository) : ViewModel(){

    var listSquads = mutableListOf<Seleccion>()
    var listCountries = mutableListOf<WorldCupTeamId>()
    var playersLiveData = MutableLiveData<List<PlayerEntity>>()

    fun searchPlayersAvailable(){
        playersLiveData.value = playerRepository.searchAllPlayers()
    }

    fun searchPlayers() : MutableList<Seleccion>{
        setCountrys()
        for(countryTeam in listCountries) {
            listSquads.add(insertPlayers(countryTeam))
        }
        return listSquads
    }


    fun insertPlayers(team : WorldCupTeamId) : Seleccion {
        val seleccion = Seleccion(team.name, mutableListOf())
        viewModelScope.launch {

            val response = playerRepository.getPlayersByCountry(team)
            if (response != null) {
                for(player in response){
                    seleccion.players.add(player!!.data)
                }
            }
        }
        return seleccion
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