package com.example.velo_a_nantes.model

import android.location.Location
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
var currentLocation:Location?=null
var currentLocationParking:Location?=null
var stationSelected:Station? = null
var parkingSelected:Parking? = null
var allStations:List<Station>?=null
var allParkings:List<Parking>?=null
@Serializable
data class Station (
    val id: Long,
    val name: String,
    val lattitude: Double,
    val longitude: Double,
    val status: String,
    val address: String,
    val bikeStands: Long,
    val availableBikes: Long,
    val availableBikeStands: Long,

    @SerialName("recordId")
    val recordID: String
){
    fun toLocation(): Location{
        val location = Location("")
        location.latitude=lattitude
        location.longitude=longitude
        return location
    }
}
@Serializable
data class Parking (
    val id: Long,
    val grpNom: String,
    val lattitude: Double,
    val longitude: Double,
    @SerialName("recordId")
    val recordID: String
) {
    fun toLocation(): Location {
        val location = Location("")
        location.latitude=lattitude
        location.longitude=longitude
        return location
    }
}