package ar.edu.unlam.figuritas.ui.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.edu.unlam.figuritas.data.PlayerRepository
import ar.edu.unlam.figuritas.data.database.entities.PlayerEntity
import ar.edu.unlam.figuritas.data.repository.DatabaseRepository
import ar.edu.unlam.figuritas.model.Seleccion
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(var databaseRepository: DatabaseRepository, var playerRepository: PlayerRepository) : ViewModel(){

    var listCountries = mutableListOf<Seleccion>()
    private val _seleccionData = MutableLiveData<List<Seleccion?>>()
    val seleccionData: MutableLiveData<List<Seleccion?>> = _seleccionData
    var playerId = 0

    init {
        setCountrys()
    }

    fun insertPlayersInSeleccion(countryId : Int) : MutableList<Seleccion>{
        var listPlayers = mutableListOf<PlayerEntity>()
        var listCountry = mutableListOf<Seleccion>()

        for (seleccion in listCountries){
            if(seleccion.idCountry == countryId){
                while(listPlayers.size != 26){
                    listPlayers.add(playerInAlbum(listPlayers.size, seleccion.idCountry))
                }
                seleccion.players = listPlayers
                listCountry.add(seleccion)
            }
        }
        return listCountry
    }

    fun insertPlayersInSelecciones() : MutableList<Seleccion>{


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

        val players = databaseRepository.getPlayersPasteForCountry(idCountry)
        for(player in players){

            if(player.position == position){
                return databaseRepository.getPlayerForId(player.playerId)!!
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
            imageUrl = "",
            imageCountry = ""
        )
    }

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