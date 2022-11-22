package com.example.velo_a_nantes.ui.parkings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.velo_a_nantes.adapter.ParkingAdapter
import com.example.velo_a_nantes.api.ParkingApi
import com.example.velo_a_nantes.api.RetrofitHelper
import com.example.velo_a_nantes.databinding.FragmentParkingBinding
import com.example.velo_a_nantes.model.allParkings
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ParkingFragment : Fragment() {

    private var _binding: FragmentParkingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val parkingViewModel =
                ViewModelProvider(this).get(ParkingViewModel::class.java)

        _binding = FragmentParkingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerViewParking
        var progressBarParking = binding.progressBarParking
        parkingViewModel.parkings.observe(viewLifecycleOwner) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = ParkingAdapter(it, requireContext())
            progressBarParking.visibility = View.GONE

            allParkings=it
        }

        val parkingApi = RetrofitHelper().getInstance().create(ParkingApi::class.java)
        GlobalScope.launch {
            val result = parkingApi.getParkings()
            Log.d("HOME", result.body().toString())
            parkingViewModel.parkings.postValue(result.body())

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

