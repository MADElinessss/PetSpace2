package com.example.petsapce_week1.reservationRelated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.ReservationMainRowChildBinding
import com.example.petsapce_week1.home.homefragment.HomeChildAdapter
import com.example.petsapce_week1.vo.ReservationReadResponse
import reserveMainData

class reserveChildAdapter(private val items: List<reserveChildData>) : RecyclerView.Adapter<reserveChildAdapter.ChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val binding = ReservationMainRowChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        // set the adapter to the ViewPager
//        holder.binding.childImg.setImageResource(items[position].childImg)
        val imageIds1 = listOf(
            R.drawable.room1_image3,
            R.drawable.downtown_image5,
            R.drawable.downtown_image2,
            R.drawable.home2
        )

        Glide.with(holder.itemView)
            .load(imageIds1[position])
            //.override(200)
            .into(holder.binding.viewpagerchildImg)
//        holder.childRecyclerView.adapter = HomeChildAdapter(items[position].childImg)
    }

    override fun getItemCount(): Int = items.size

    inner class ChildViewHolder(val binding: ReservationMainRowChildBinding) : RecyclerView.ViewHolder(binding.root){

    }
}
