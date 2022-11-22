package com.example.velo_a_nantes.ui.station

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.velo_a_nantes.R
import com.example.velo_a_nantes.model.stationSelected

class DetailStationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_station)

        val stationName = findViewById<TextView>(R.id.nameDetail)
        var buttonOpen = findViewById<Button>(R.id.buttonOpenMap2)

        stationSelected?.let{station->
            stationName.text = station.name
            buttonOpen.setOnClickListener{
                val gmmIntentUri =
                    Uri.parse("geo:0,0?q=${station.lattitude},${station.longitude}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }
}