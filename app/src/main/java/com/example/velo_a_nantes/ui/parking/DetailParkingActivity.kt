package com.example.velo_a_nantes.ui.parking

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.velo_a_nantes.R
import com.example.velo_a_nantes.model.parkingSelected

class DetailParkingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_parking)

        val parkingName = findViewById<TextView>(R.id.grpNomDetail)
        var buttonOpen = findViewById<Button>(R.id.buttonOpenMap)

        parkingSelected?.let{parking->
            parkingName.text = parking.grpNom
            buttonOpen.setOnClickListener{
                val gmmIntentUri =
                    Uri.parse("geo:0,0?q=${parking.lattitude},${parking.longitude}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }
}