package com.example.velo_a_nantes.ui.parking

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.velo_a_nantes.R
import com.example.velo_a_nantes.databinding.ActivityParkingMapsBinding
import com.example.velo_a_nantes.model.allParkings
import com.example.velo_a_nantes.model.currentLocation
import com.example.velo_a_nantes.model.parkingSelected
import com.google.android.gms.location.FusedLocationProviderClient

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ParkingMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityParkingMapsBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityParkingMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapParking) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        parkingSelected?.let { parking ->
            val position = LatLng(parking.lattitude, parking.longitude)
            mMap.addMarker(MarkerOptions()
                .position(position)
                .title(parking.grpNom)
                .icon(BitmapFromVector(this, R.drawable.ic_baseline_pedal_bike_24))
            )
            allParkings?.let{
                for(parkingSt in it){

                    val parkingLatLng = LatLng(parkingSt.lattitude, parkingSt.longitude)
                    if (parkingSt.id == parking.id){
                        mMap.addMarker(MarkerOptions()
                            .position(parkingLatLng)
                            .title(parkingSt.grpNom)
                            .icon(BitmapFromVector(this, R.drawable.ic_baseline_pedal_bike_24))
                        )
                    }
                    else{
                        mMap.addMarker(MarkerOptions()
                            .position(parkingLatLng)
                            .title(parkingSt.grpNom)
                        )
                    }


                }

            }

            // Add a marker in Sydney and move the camera
            val sydney = LatLng(parking.lattitude, parking.longitude)
            if (currentLocation!=null){

            }

            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18f))
        }

    }
    private fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        // below line is use to generate a drawable.
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

        // below line is use to set bounds to our vector drawable.
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )

        // below line is use to create a bitmap for our
        // drawable which we have added.
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        // below line is use to add bitmap in our canvas.
        val canvas = Canvas(bitmap)

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas)

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

}