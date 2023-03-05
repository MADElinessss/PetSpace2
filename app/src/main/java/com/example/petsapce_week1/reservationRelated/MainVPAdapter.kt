package com.example.petsapce_week1.reservationRelated

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class MainVPAdapter(fr: Fragment, isLogin : Boolean):FragmentStateAdapter(fr) {
    val loginCheck = isLogin
    override fun getItemCount(): Int=2

    override fun createFragment(position: Int): Fragment { //포지션에 따라 어떤 프레그먼트를 보여줄것인지

        return if(loginCheck){
            if(position == 0){
                LeftFragment()
            } else{
                RightFragment()
            }
        } else{
            if(position == 1){
                ReservationNoPlaceFragment()
            } else{
                VisitedNoPlaceToGoFragment()
            }
        }
    }
}