package com.example.petsapce_week1.accommodation.scroll

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.ActivityHostMoreBinding
import com.example.petsapce_week1.databinding.ActivitySeoulAccommoBinding
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.placetogo.NoPlaceToGoFragment
import com.example.petsapce_week1.placetogo.PlaceToGoFragment
import com.example.petsapce_week1.placetogo.PlaceToGoRegionAdapter
import com.example.petsapce_week1.vo.FavoriteBackendResponse
import retrofit2.Retrofit

class AccHostViewMoreActivity :AppCompatActivity() {
    private lateinit var binding: ActivityHostMoreBinding
    //레트로핏 객체 생성
    var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    //서비스 객체 생성
    var api: AccomoService = retrofit.create(AccomoService::class.java)

    var accommoList: MutableList<FavoriteBackendResponse.Result.Favorite> = mutableListOf()

    lateinit var roomId: String
    var isLast : Boolean = false
    var accessToken : String ?= null
    var region : String ?= null
    var reviewCount :Int ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHostMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        accommoList = intent.getSerializableExtra("accommoList") as MutableList<FavoriteBackendResponse.Result.Favorite>
        isLast = intent.getBooleanExtra("isLast", false)
        accessToken = intent.getStringExtra("accessToken")
        region = intent.getStringExtra("region")
        reviewCount = intent.getIntExtra("reviewCount", 0)
        Log.d("함께 서울", "$accommoList")
        if(accommoList.isEmpty()){
            supportFragmentManager.beginTransaction()
                .replace(R.id.thisLayout, NoPlaceToGoFragment())
                //.addToBackStack(null)
                .commit()
        }

        binding.tvHostname.text = "${intent.getStringExtra("name")}님의 숙소"
        binding.btnHostClose.setOnClickListener {
            finish()
        }

//        //initRecyclerView()
//        var adapter: PlaceToGoRegionAdapter = PlaceToGoRegionAdapter(accommoList, accessToken!!)
//        binding.hostScrollview.layout(LinearLayoutManager(this))
//        binding.recyclerviewMainHome.layoutManager = LinearLayoutManager(this)
//        binding.recyclerviewMainHome.adapter = adapter
//        binding.recyclerviewMainHome.isNestedScrollingEnabled = true
//        binding.recyclerviewMainHome.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//            var isLoading = false
//            var isLastPage = false
//            var currentPage = 0
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val layoutManager = binding.recyclerviewMainHome.layoutManager as LinearLayoutManager
//                val totalItemCount = layoutManager.itemCount
//                Log.d("부산1", totalItemCount.toString())
//                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
//                Log.d("부산2", lastVisibleItem.toString())
//                if (!isLoading && !isLastPage && lastVisibleItem == totalItemCount - 1)  {
//                    currentPage++
//                    isLoading = true
//                    region?.let {
//                        api.getFavorites(accessToken!!, it, page = currentPage, size = 5).enqueue(object : retrofit2.Callback<FavoriteBackendResponse> {
//                            override fun onResponse(
//                                call: retrofit2.Call<FavoriteBackendResponse>,
//                                response: retrofit2.Response<FavoriteBackendResponse>
//                            ) {
//                                Log.d("함께 갈 곳 스크롤", currentPage.toString())
//                                val data = response.body()
//                                Log.d("함께 갈 곳 스크롤", data.toString())
//                                if (data?.result != null) {
//                                    Log.d("함께 갈 곳 isLast", data.result.isLast.toString())
//                                    if(data.result.isLast){
//                                        isLastPage = true
//                                    }
//                                    else{
//                                        adapter = PlaceToGoRegionAdapter(accommoList, accessToken!!)
//                                        binding.hostScrollview.
//                                        binding.host.layoutManager =
//                                            LinearLayoutManager(this@AccHostViewMoreActivity)
//                                        binding.recyclerviewMainHome.adapter = adapter
//                                        binding.recyclerviewMainHome.isNestedScrollingEnabled = true
//                                    }
//                                }
//                                else{
//                                    val noplacetogofragment = NoPlaceToGoFragment()
//                                    val placetogofragment = PlaceToGoFragment()
//                                    Log.d("함께 갈 곳 2222", "222222")
//                                    placetogofragment.parentFragmentManager
//                                        .beginTransaction()
//                                        .replace(R.id.thisLayout, noplacetogofragment)
//                                        .addToBackStack(null)
//                                        .commit()
//                                }
//                                isLoading = false
//
//                            }
//
//                            override fun onFailure(call: retrofit2.Call<FavoriteBackendResponse>, t: Throwable) {
//                                Log.d("함께 갈 곳 스크롤ㄴㄴ", "ㄴㄴ")
//                                isLoading = false
//
//                            }
//                        })
//
//                    }
//                }
//            }
//        })
    }
}