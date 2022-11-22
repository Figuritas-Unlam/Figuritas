package ar.edu.unlam.figuritas.model


import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import ar.edu.unlam.figuritas.model.response.PlayerResponseData

class Seleccion(val nameCountry: String, val displayNameCountry : String, val imageCountry : String, val idCountry : Int, var players: List<PlayerEntity>)