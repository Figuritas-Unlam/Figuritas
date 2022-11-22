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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(var databaseRepository: DatabaseRepository, var playerRepository: PlayerRepository) : ViewModel(){

    var listCountries = mutableListOf<Seleccion>()
    private val _seleccionData = MutableLiveData<List<Seleccion?>>()
    val seleccionData: MutableLiveData<List<Seleccion?>> = _seleccionData


    init {
        setCountrys()
        insertPlayersInSeleccion()
    }

/*
    fun buscarcoso() : MutableList<Seleccion>{
        CoroutineScope(Dispatchers.IO).launch {
            var listaEntidades = mutableListOf<PlayerEntity>()

            for (responseplayer in playerRepository.getPlayersByCountry(WorldCupTeamId.ARGENTINA)!!) {
                if (responseplayer != null) {
                    listaEntidades.add(
                        PlayerEntity(
                            responseplayer.data.playerId,
                            responseplayer.data.name,
                            responseplayer.data.height,
                            responseplayer.data.weight,
                            responseplayer.data.birthdate,
                            responseplayer.data.teamId,
                            responseplayer.data.countryId,
                            0,
                            false,
                            "",
                            "",
                            ""
                        )
                    )
                }
            }
            listSquads.add(Seleccion(WorldCupTeamId.ARGENTINA.name, listaEntidades))

        }
        return listSquads
    }
    */


    fun insertPlayersInSeleccion() : MutableList<Seleccion>{


        for(seleccion in listCountries){

            var listPlayers = mutableListOf<PlayerEntity>()
            while(listPlayers.size != 26){
                listPlayers.add(playerInAlbum(listPlayers.size, seleccion.idCountry))

            }
            seleccion.players = listPlayers
        }
        return listCountries
    }

    private fun playerInAlbum(position: Int, idCountry : Int) : PlayerEntity{

        val players = databaseRepository.getPlayersForCountry(idCountry)
        for(player in players){

            if(player.position == position){
                return player
            }

        }
        return PlayerEntity(
            0,
            "?",
            "?",
            "?",
            "?",
            "?",
            0,
            0,
            0,
            false,
            false,
            "?",
            "?",
            position
        )
    }
    /*
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
                0
            )
        }
    }
*/
    fun setCountrys(){

        listCountries.add(Seleccion("QATAR", "QAT", "https://cdn.sportmonks.com/images/countries/png/short/qa.png",
            74505, mutableListOf()
        ))
        listCountries.add(Seleccion("ECUADOR", "ECU", "https://cdn.sportmonks.com/images/countries/png/short/ec.png",
            459, mutableListOf()
        ))
        listCountries.add(Seleccion("SENEGAL", "SEN", "https://cdn.sportmonks.com/images/countries/png/short/sn.png",
            200, mutableListOf()
        ))
        listCountries.add(Seleccion("NETHERLANDS", "NHL", "https://cdn.sportmonks.com/images/countries/png/short/nl.png",
            38, mutableListOf()
        ))


        listCountries.add(Seleccion("ENGLAND", "ENG", "https://cdn.sportmonks.com/images/countries/png/short/gb.png",
            462, mutableListOf()
        ))
        listCountries.add(Seleccion("IRAN", "IRN", "https://cdn.sportmonks.com/images/countries/png/short/ir.png",
            488, mutableListOf()
        ))
        listCountries.add(Seleccion("UNITED STATES", "USA", "https://cdn.sportmonks.com/images/countries/png/short/us.png",
            3483, mutableListOf()
        ))
        listCountries.add(Seleccion("WALES", "WAL", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Flag_of_Wales.svg/200px-Flag_of_Wales.svg.png",
            515, mutableListOf()
        ))


        listCountries.add(Seleccion("ARGENTINA", "ARG", "https://cdn.sportmonks.com/images/countries/png/short/ar.png",
            44, mutableListOf()
        ))
        listCountries.add(Seleccion("SAUDI ARABIA", "SAB", "https://cdn.sportmonks.com/images/countries/png/short/sa.png",
            35376, mutableListOf()
        ))
        listCountries.add(Seleccion("MEXICO", "MEX", "https://cdn.sportmonks.com/images/countries/png/short/mx.png",
            458, mutableListOf()
        ))
        listCountries.add(Seleccion("POLAND", "POL", "https://cdn.sportmonks.com/images/countries/png/short/pl.png",
            2, mutableListOf()
        ))


        listCountries.add(Seleccion("FRANCE", "FRA", "https://cdn.sportmonks.com/images/countries/png/short/fr.png",
            17, mutableListOf()
        ))
        listCountries.add(Seleccion("AUSTRALIA", "AUS", "https://cdn.sportmonks.com/images/countries/png/short/au.png",
            98, mutableListOf()
        ))
        listCountries.add(Seleccion("DENMARK", "DEN", "https://cdn.sportmonks.com/images/countries/png/short/dk.png",
            320, mutableListOf()
        ))
        listCountries.add(Seleccion("TUNISIA", "TUN", "https://cdn.sportmonks.com/images/countries/png/short/tn.png",
            1439, mutableListOf()
        ))


        listCountries.add(Seleccion("SPAIN", "SPA", "https://cdn.sportmonks.com/images/countries/png/short/es.png",
            32, mutableListOf()
        ))
        listCountries.add(Seleccion("COSTA RICA", "COS", "https://cdn.sportmonks.com/images/countries/png/short/cr.png",
            1739, mutableListOf()
        ))
        listCountries.add(Seleccion("GERMANY", "GER", "https://cdn.sportmonks.com/images/countries/png/short/de.png",
            11, mutableListOf()
        ))
        listCountries.add(Seleccion("JAPAN", "JAP", "https://cdn.sportmonks.com/images/countries/png/short/jp.png",
            479, mutableListOf()
        ))


        listCountries.add(Seleccion("BELGIUM", "BEL", "https://cdn.sportmonks.com/images/countries/png/short/be.png",
            556, mutableListOf()
        ))
        listCountries.add(Seleccion("CANADA", "CAN", "https://cdn.sportmonks.com/images/countries/png/short/ca.png",
            1004, mutableListOf()
        ))
        listCountries.add(Seleccion("MOROCCO", "MOR", "https://cdn.sportmonks.com/images/countries/png/short/ma.png",
            1424, mutableListOf()
        ))
        listCountries.add(Seleccion("CROATIA", "CRO", "https://cdn.sportmonks.com/images/countries/png/short/hr.png",
            226, mutableListOf()
        ))


        listCountries.add(Seleccion("BRAZIL", "BRA", "https://cdn.sportmonks.com/images/countries/png/short/br.png",
            5, mutableListOf()
        ))
        listCountries.add(Seleccion("SERBIA", "SER", "https://cdn.sportmonks.com/images/countries/png/short/rs.png",
            296, mutableListOf()
        ))
        listCountries.add(Seleccion("SWITZERLAND", "SWI", "https://cdn.sportmonks.com/images/countries/png/short/ch.png",
            62, mutableListOf()
        ))
        listCountries.add(Seleccion("CAMEROON", "CAM", "https://cdn.sportmonks.com/images/countries/png/short/cm.png",
            593, mutableListOf()
        ))


        listCountries.add(Seleccion("PORTUGAL", "POR", "https://cdn.sportmonks.com/images/countries/png/short/pt.png",
            20, mutableListOf()
        ))
        listCountries.add(Seleccion("GHANA", "GHA", "https://cdn.sportmonks.com/images/countries/png/short/gh.png",
            468, mutableListOf()
        ))
        listCountries.add(Seleccion("URUGUAY", "URU", "https://cdn.sportmonks.com/images/countries/png/short/uy.png",
            158, mutableListOf()
        ))
        listCountries.add(Seleccion("KOREA REPUBLIC", "KOR", "https://cdn.sportmonks.com/images/countries/png/short/kr.png",
            712, mutableListOf()
        ))

    }

}