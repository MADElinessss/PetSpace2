package com.example.petsapce_week1.reservationRelated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petsapce_week1.databinding.FragmentVisitedNoplaceBinding

class RightFragment : Fragment() {
    lateinit var binding:FragmentVisitedNoplaceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVisitedNoplaceBinding.inflate(layoutInflater)



        // Inflate the layout for this fragment
        return binding.root
    }

}