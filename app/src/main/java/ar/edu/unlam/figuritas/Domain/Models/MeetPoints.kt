package ar.edu.unlam.figuritas.Domain.Models

import com.google.android.gms.maps.model.LatLng

enum class MeetPoints(val namePoint: String, val coordinates : LatLng) {
    POINT_ONE("PointOne", LatLng(-34.601732, -58.469339)),
    POINT_TWO("PointTwo", LatLng(-34.587871, -58.463758)),
    POINT_THREE("PointThree", LatLng(-34.587862, -58.395270)),
    POINT_FOUR("PointFour", LatLng(-34.614463, -58.404880)),
    POINT_FIVE("PointFive", LatLng(-34.641201, -58.479896)),
    POINT_SIX("PointSix", LatLng(-34.560833, -58.492334))
}