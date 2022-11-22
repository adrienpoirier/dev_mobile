package com.example.velo_a_nantes.ui.parkings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.velo_a_nantes.model.Parking

class ParkingViewModel : ViewModel() {

    private val _parkings = MutableLiveData<List<Parking>>().apply {
        value = ArrayList()
    }
    val parkings: MutableLiveData<List<Parking>> = _parkings
}