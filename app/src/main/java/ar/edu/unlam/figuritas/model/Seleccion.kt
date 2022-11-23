package ar.edu.unlam.figuritas.model


import ar.edu.unlam.figuritas.data.database.entities.PlayerEntity

class Seleccion(val nameCountry: String, val displayNameCountry : String, val imageCountry : String, val idCountry : Int, var players: List<PlayerEntity>)