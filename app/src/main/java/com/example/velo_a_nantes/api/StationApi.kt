package com.example.velo_a_nantes.api

import com.example.velo_a_nantes.model.Station
import retrofit2.Response
import retrofit2.http.GET

interface StationApi {
    @GET("api/stations")
    suspend fun getStations(): Response<List<Station>>

}