package com.example.velo_a_nantes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.velo_a_nantes.R
import com.example.velo_a_nantes.model.Parking
import com.example.velo_a_nantes.model.parkingSelected
import com.example.velo_a_nantes.ui.parking.ParkingMapsActivity

class ParkingAdapter(private val parkings:List<Parking>, private val context: Context) :
    RecyclerView.Adapter<ParkingAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.findViewById(R.id.cardViewParking)
        val grpNom : TextView = itemView.findViewById(R.id.grpNom)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_item_parking, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parking = parkings[position]
        parkingSelected = parking
        holder.grpNom.text = parking.grpNom


        holder.cardView.setOnClickListener{
            val intent = Intent(context, ParkingMapsActivity::class.java)
            parkingSelected=parking
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return parkings.size
    }

}