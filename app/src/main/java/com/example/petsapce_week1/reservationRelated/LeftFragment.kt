package com.example.petsapce_week1.reservationRelated

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentLeftBinding
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.network.homeAPI
import reserveMainData
import retrofit2.Retrofit


class LeftFragment : Fragment() {
    private lateinit var binding: FragmentLeftBinding

    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api: homeAPI = retrofit.create(homeAPI::class.java)

    var accessToken: String? = null
    var dataList = ArrayList<reserveMainData>()
    lateinit var adapter: reserveMainAdapter

    lateinit var roomId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeftBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        val atpref = requireContext().getSharedPreferences("accessToken", Context.MODE_PRIVATE)
        accessToken = atpref.getString("accessToken", "default")
        accessToken = "Bearer $accessToken"

        initRecyclerView()

        return binding.root
    }


    private fun initRecyclerView() {

        //기존 adapter(recyclerview adpater)
        binding.recyclerviewMain.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )


        val accommodationList = listOf(
            reserveMainData(arrayListOf(reserveChildData(R.drawable.downtown_image5), reserveChildData(R.drawable.downtown_image2),reserveChildData(R.drawable.downtown_image2), reserveChildData(R.drawable.downtown_image2)),"신라호텔", 3.33f,"서울시 중구","2023년 02월 14일 ~ 2023년 02월 16일", 363000,5,17),
//            reserveMainData(arrayListOf(reserveChildData(resources.getResourceName(drawableRes1)), reserveChildData(resources.getResourceName(drawableRes2)), reserveChildData(resources.getResourceName(drawableRes2)), reserveChildData(resources.getResourceName(drawableRes2))),"자연 속 깊은 휴식과 치유를 위한 아름다운 한옥 독채", 4.25f,"횡성시 청일면","2023년 03월 05일 ~ 2023년 03월 07일", 320000,3,7),
//            reserveMainData(arrayListOf(reserveChildData(resources.getResourceName(drawableRes1)), reserveChildData(resources.getResourceName(drawableRes2)), reserveChildData(resources.getResourceName(drawableRes2)), reserveChildData(resources.getResourceName(drawableRes2))),"파주 힐링 글램핑", 4.17f,"파주시 탄현면","2023년 03월 08일 ~ 2023년 03월 09일", 400000,8,10),
//            reserveMainData(arrayListOf(reserveChildData(resources.getResourceName(drawableRes1)), reserveChildData(resources.getResourceName(drawableRes2)), reserveChildData(resources.getResourceName(drawableRes2)), reserveChildData(resources.getResourceName(drawableRes2))),"위워크 선릉2호점", 4.0f,"서울시 강남구","2023년 03월 08일 ~ 2023년 03월 09일", 400000,8,1)
        )

        adapter = reserveMainAdapter(accommodationList)
        Log.d("예약 데이터", accommodationList.toString())
        binding.recyclerviewMain.adapter = adapter
        binding.recyclerviewMain.isNestedScrollingEnabled = false

    }


    /*  private fun getAccessToken() {
          val atpref = requireContext().getSharedPreferences("accessToken", Context.MODE_PRIVATE)
          accessToken = atpref.getString("accessToken", "default")
          accessToken = "Bearer $accessToken"
      }*/


}