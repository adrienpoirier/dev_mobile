package com.example.velo_a_nantes.api

import com.example.velo_a_nantes.model.Parking
import retrofit2.Response
import retrofit2.http.GET

interface ParkingApi {
    @GET("api/parkings")
    suspend fun getParkings(): Response<List<Parking>>

}