package com.example.petsapce_week1.reservationRelated

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.accommodation.AccMainActivity
import com.example.petsapce_week1.databinding.*
import com.example.petsapce_week1.review.ReviewPostActivity
import reserveMainData
import java.text.DecimalFormat

class reserveMainAdapter(var items: List<reserveMainData>) :
    RecyclerView.Adapter<reserveMainAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ReservationMainRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: reserveMainData) {

            val springDotsIndicator = binding.dotsIndicator
            val viewPager = binding.childViewPager
            val adapter = reserveChildAdapter(data.imgList)
            viewPager.adapter = adapter
            springDotsIndicator.attachTo(viewPager)

            val priceCut = DecimalFormat("#,###")
            val price = priceCut.format(data.price)

            binding.apply {
                tvAccommoName.text = data.roomName
                tvReservationDate.text = data.date
                textLoc.text = data.location
                textPrice.text = "$price 원"
                textReview.text = "(${data.review})"
                textScore.text = data.score.toString()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ReservationMainRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val accommodation = items[position]
        holder.bind(accommodation)
        Log.d("예약 바인드", accommodation.roomName.toString())
        Log.d("예약 바인드 position", position.toString())

        val roomIDNext = items[position].roomID

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,AccMainActivity::class.java)
            intent.putExtra("content",roomIDNext)
            ContextCompat.startActivity(holder.itemView.context,intent,null)
            Log.d("content",roomIDNext.toString())
        }


        holder.binding.apply {
            topcardview.cardElevation = 0f
            btnHeartAfter.setOnClickListener {
                btnHeartAfter.isSelected = btnHeartAfter.isSelected != true
            }

        }

        holder.binding.btnReservation.setOnClickListener {
            val intent = Intent(holder.itemView.context,ReviewPostActivity::class.java)
            intent.putExtra("content",roomIDNext)
            ContextCompat.startActivity(holder.itemView.context,intent,null)
        }
        holder.bind(items[position])

    }

    override fun getItemCount(): Int {
        return items.size
    }

}



