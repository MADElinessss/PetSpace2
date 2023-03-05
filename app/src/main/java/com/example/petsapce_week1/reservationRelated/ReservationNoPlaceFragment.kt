package com.example.petsapce_week1.reservationRelated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.petsapce_week1.databinding.FragmentNoPlaceToGoBinding
import com.example.petsapce_week1.databinding.FragmentReservationNoplaceBinding

class ReservationNoPlaceFragment : Fragment(){

    private lateinit var binding : FragmentReservationNoplaceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReservationNoplaceBinding.inflate(layoutInflater)

        return binding.root
    }

}