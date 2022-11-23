package ar.edu.unlam.figuritas.ui.fragments

import ar.edu.unlam.figuritas.domain.response.PlayerResponseData

interface FragmentPlayerCommunicator {
    fun passPlayerDataToFragment(data: PlayerResponseData)
}