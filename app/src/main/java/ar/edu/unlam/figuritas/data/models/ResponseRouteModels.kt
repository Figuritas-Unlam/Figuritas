package ar.edu.unlam.figuritas.Data.models

import ar.edu.unlam.figuritas.Domain.Models.Route
import com.google.gson.annotations.SerializedName

data class RouteResponse (@SerializedName("features") val features: List<Feature>)

fun RouteResponse.mapToRoute() = Route(features[0].geometry.coordinates)

data class Feature(@SerializedName("geometry") val geometry: Geometry)

data class Geometry(@SerializedName("coordinates") val coordinates: List<List<Double>>)