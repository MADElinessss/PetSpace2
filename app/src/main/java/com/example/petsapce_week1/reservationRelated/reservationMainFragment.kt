package com.example.petsapce_week1.reservationRelated

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petsapce_week1.databinding.FragmentReservationMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class reservationMainFragment : Fragment() {

    private lateinit var binding : FragmentReservationMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservationMainBinding.inflate(layoutInflater)

        val isLogin = arguments?.getBoolean("isLogin")
        Log.d("예약 Fragment", "isLogin = $isLogin")

        Log.d("예약에서 로그인 여부 확인", isLogin.toString())
        val tabTitleArray = arrayOf(
            "방문 완료",
            "예약 완료",
        )

        val mainVPAdapter = isLogin?.let { MainVPAdapter(this, it) }
        binding.vpMain.adapter = mainVPAdapter // set adapter before attaching mediator
        binding.vpMain.isUserInputEnabled = false

        // check if adapter is not null before attaching mediator
        mainVPAdapter?.let {
            TabLayoutMediator(binding.tabMain,binding.vpMain){tab,position->
                tab.text = tabTitleArray[position]
            }.attach()
        }

        return binding.root

    }

}