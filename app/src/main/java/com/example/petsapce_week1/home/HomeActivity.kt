package com.example.petsapce_week1.home

//import com.example.petsapce_week1.home.homefragment.HomeFragment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.ProfileMenuFragment
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.ActivityHomeBinding
import com.example.petsapce_week1.home.homefragment.HomeFragment
import com.example.petsapce_week1.placetogo.NoLoginPlacetogoFragment
import com.example.petsapce_week1.placetogo.PlaceToGoFragment
import com.example.petsapce_week1.reservationRelated.reservationMainFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    var accessToken : String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

        binding.mainBtmNav.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_heart -> {
                        val isLogin = LoginCheck()
                        Log.d("예약 아니고 함께갈곳", "${isLogin}")
                        if(isLogin){
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.main_frm, PlaceToGoFragment())
                                .commitAllowingStateLoss()
                        }
                        else{
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.main_frm, NoLoginPlacetogoFragment())
                                .commitAllowingStateLoss()
                        }
                    }
                    R.id.menu_main_btm_nav_reserve -> {
                        val bundle = Bundle()
                        val isUserLoggedIn = LoginCheck()
                        Log.d("dlrjs 예약", "$isUserLoggedIn")
                        bundle.putBoolean("isLogin", isUserLoggedIn)
                        val passBundleFragment = reservationMainFragment()
                        passBundleFragment.arguments = bundle
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, passBundleFragment)
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_profile -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, ProfileMenuFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.menu_main_btm_nav_home
        }

    }
    fun LoginCheck(): Boolean {
        val isLogin : Boolean
        getAccessToken()

        if(accessToken != "default") {
            isLogin = true
        }
        else{
            Log.d("함께 갈 곳", "비었음")
            isLogin = false
        }
        return isLogin
    }

    private fun getAccessToken() {
        val atpref = getSharedPreferences("accessToken", MODE_PRIVATE)
        if(atpref != null){
            accessToken = atpref.getString("accessToken", "default")
        }
        Log.d("홈 화면 accessToken11","$accessToken")
    }
}