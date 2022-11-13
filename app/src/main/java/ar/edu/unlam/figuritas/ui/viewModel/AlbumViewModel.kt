package ar.edu.unlam.figuritas.ui.viewModel

import androidx.lifecycle.ViewModel
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor() : ViewModel(){

    private var listCountrys = mutableListOf<String>()
    private var listPlayers = mutableListOf<PlayerEntity>()


    fun setCountrys(){

        listCountrys.add("Qatar")
        listCountrys.add("Ecuador")
        listCountrys.add("Senegal")
        listCountrys.add("Netherlands")

        listCountrys.add("England")
        listCountrys.add("IR Iran")
        listCountrys.add("USA")
        listCountrys.add("Wales")

        listCountrys.add("Argentina")
        listCountrys.add("Saudi Arabia")
        listCountrys.add("Mexico")
        listCountrys.add("Poland")

        listCountrys.add("France")
        listCountrys.add("Australia")
        listCountrys.add("Denmark")
        listCountrys.add("Tunisia")

        listCountrys.add("Spain")
        listCountrys.add("Costa Rica")
        listCountrys.add("Germany")
        listCountrys.add("Japan")

        listCountrys.add("Belgium")
        listCountrys.add("Canada")
        listCountrys.add("Morocco")
        listCountrys.add("Croatia")

        listCountrys.add("Brazil")
        listCountrys.add("Serbia")
        listCountrys.add("Switzerland")
        listCountrys.add("Cameroon")

        listCountrys.add("Portugal")
        listCountrys.add("Ghana")
        listCountrys.add("Uruguay")
        listCountrys.add("Korea Republic")


    }
}