package com.example.petsapce_week1.reservationRelated

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.petsapce_week1.databinding.FragmentNoPlaceToGoBinding
import com.example.petsapce_week1.databinding.FragmentReservationNoplaceBinding
import com.example.petsapce_week1.databinding.FragmentVisitedNoplaceBinding
import com.example.petsapce_week1.home.HomeResearchActivity

class VisitedNoPlaceToGoFragment : Fragment(){

    private lateinit var binding : FragmentVisitedNoplaceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentVisitedNoplaceBinding.inflate(layoutInflater)

        binding.tvSearch.setOnClickListener {
            val intent = Intent(requireContext(), HomeResearchActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

}