package com.example.petsapce_week1.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.databinding.ActivityHome2Binding
import com.example.petsapce_week1.home.homefragment.SortViewModel
import com.example.petsapce_week1.network.RetrofitHelperHome
import com.example.petsapce_week1.network.homeAPI
import com.example.petsapce_week1.vo.HomeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class Home2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityHome2Binding
    val btn1House = "HOUSE"
    val btn2Campsite = "CAMPSITE"
    val btn3Downtown = "DOWNTOWN"
    val btn4Country = "COUNTRY"
    val btn5Beach = "BEACH"

    val sortDefault = "ID_DESC"
    val sortPriceDesc = "PRICE_ASC"
    val sortPriceAsc = "PRICE_DESC"
    val sortReviewCount = "REVIEW_COUNT_DESC"
    val sortReviewScore = "AVERAGE_REVIEW_SCORE_DESC"

    //레트로핏 객체 생성
    var retrofit: Retrofit = RetrofitHelperHome.getRetrofitInstance()

    //서비스 객체 생성
    var api: homeAPI = retrofit.create(homeAPI::class.java)

    lateinit var viewModel: SortViewModel

    //child apdater

    var dataList = ArrayList<Home2MainData>()
    lateinit var spinner: Spinner
    lateinit var roomId: String

    lateinit var adapter: Home2MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHome2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(SortViewModel::class.java)

        initFirst()
        initSpinner()
        initButtonSort()
        initRecyclerView()

    }

    private fun initFirst() {
        val searchText = intent.getStringExtra("searchText")
        val startDay = intent.getStringExtra("startDay")
        val endDay = intent.getStringExtra("endDay")
        val adult = intent.getIntExtra("adult",-1)
        val child = intent.getIntExtra("child",-1)
        val animal = intent.getIntExtra("animal",-1)
        Log.d("tag",searchText.toString())
        Log.d("tag1",startDay.toString())
        Log.d("tag2",endDay.toString())
        Log.d("tag3",adult.toString())
        Log.d("tag4",child.toString())
        Log.d("tag5",animal.toString())
    }

    private fun initSpinner() {
        spinner = binding.spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d(
                    "MainActivity",
                    "onItemSelected : $position, ${spinner.getItemAtPosition(position)}"
                )
                when (spinner.getItemAtPosition(position)) {
                    "최근등록순" -> {
                        updateTripple(0,sortDefault,"")

                    }
                    "높은가격순" -> {
                        updateTripple(0,sortPriceAsc,"")
                    }
                    "낮은가격순" -> {
                        updateTripple(0,sortPriceDesc,"")
                    }
                    "평점높은순" -> {
                        updateTripple(0,sortReviewScore,"")
                    }
                    "리뷰많은순" -> {
                        updateTripple(0,sortReviewCount,"")
                    }
                    else -> {
                        updateTripple(0,sortPriceAsc,"")
                    }
                }
            }
        }

    }


    private fun initButtonSort() {
        binding.apply {
            b1.setOnClickListener {
                updateTripple(0,"", btn1House)
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    @SuppressLint("NotifyDataSetChanged")
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        Log.d(
                            "MainActivity",
                            "onItemSelected : $position, ${spinner.getItemAtPosition(position)}"
                        )
                        when (spinner.getItemAtPosition(position)) {
                            "최근등록순" -> {
                                updateTripple(0,sortDefault, btn1House)
                            }
                            "높은가격순" -> {
                                updateTripple(0,sortPriceAsc, btn1House)
                            }
                            "낮은가격순" -> {
                                updateTripple(0,sortPriceDesc, btn1House)
                            }
                            "평점높은순" -> {
                                updateTripple(0,sortReviewScore, btn1House)
                            }
                            "리뷰많은순" -> {
                                updateTripple(0,sortReviewCount, btn1House)
                            }
                            else -> {
                                updateTripple(0,sortDefault, btn1House)
                            }
                        }
                    }
                }
            }

            b2.setOnClickListener {
                updateTripple(0,"",btn2Campsite)
            }
            b3.setOnClickListener {
                updateTripple(0,"",btn3Downtown)
            }
            b4.setOnClickListener {
                updateTripple(0,"",btn4Country)
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    @SuppressLint("NotifyDataSetChanged")
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        Log.d(
                            "MainActivity",
                            "onItemSelected : $position, ${spinner.getItemAtPosition(position)}"
                        )
                        when (spinner.getItemAtPosition(position)) {
                            "최근등록순" -> {
                                updateTripple(0,sortDefault, btn4Country)
                            }
                            "높은가격순" -> {
                                updateTripple(0,sortPriceAsc, btn4Country)
                            }
                            "낮은가격순" -> {
                                updateTripple(0,sortPriceDesc, btn4Country)
                            }
                            "평점높은순" -> {
                                updateTripple(0,sortReviewScore, btn4Country)
                            }
                            "리뷰많은순" -> {
                                updateTripple(0,sortReviewCount, btn4Country)
                            }
                            else -> {
                                updateTripple(0,sortDefault, btn4Country)
                            }
                        }
                    }
                }


            }
            b5.setOnClickListener {
                updateTripple(0,"",btn5Beach)

            }

        }

    }

    fun updateTripple(page: Int, sort: String, category: String) {

        api.getTriple(page, sort, category).enqueue(object : Callback<HomeResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<HomeResponse>,
                response: Response<HomeResponse>
            ) {
                val usersSort = response.body()
                if (usersSort != null) {
//                    Log.d("tripple", usersSort.result.toString())
                    val resultSize = usersSort.result.size
                    val dataList = ArrayList<Home2MainData>()
                    var statdate = ""
                    var endDate = ""


                    for (i in 0 until resultSize) {
                        roomId = usersSort.result[i].roomId.toString()
//                        val availDaysList = usersSort.result[i].availableDays.size
                        val availImageSize = usersSort.result[i].roomImages.size

                        var childataList = ArrayList<Home2ChildData>()
                        for (j in 0 until availImageSize) {
                            childataList.add(Home2ChildData(usersSort.result[i].roomImages[j]))

                        }

                        dataList.add(
                            Home2MainData(
                                childataList,
                                usersSort.result[i].averageReviewScore,
                                usersSort.result[i].city + ", " + usersSort.result[i].district,
                                "$statdate~$endDate",
                                usersSort.result[i].price,
                                usersSort.result[i].numberOfReview,
                                usersSort.result[i].roomId

                            )

                        )
                    }

                    adapter.items = dataList
                    adapter.notifyDataSetChanged()


                } else {
                    Log.d("PRICE_DESC", response.code().toString())

                }
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("PRICE_DESC", t.message.toString())
            }
        })

    }


    private fun initRecyclerView() {

        //기존 adapter(recyclerview adpater)
        binding.recyclerviewMain.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        adapter = Home2MainAdapter(dataList)
        binding.recyclerviewMain.adapter = adapter
        binding.recyclerviewMain.isNestedScrollingEnabled = false


    }

}