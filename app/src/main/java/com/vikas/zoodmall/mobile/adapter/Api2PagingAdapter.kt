package com.vikas.zoodmall.mobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vikas.zoodmall.mobile.R
import com.vikas.zoodmall.mobile.view.ApiUIModel

class Api2PagingAdapter(diffCallback: DiffUtil.ItemCallback<ApiUIModel>):
 PagingDataAdapter<ApiUIModel, Api2PagingAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bannerImage: ImageView = view.findViewById(R.id.glide_image)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val banner = getItem(position)

        Glide.with(holder.itemView.context)
            .load(banner?.imageUrl)
            .placeholder(R.drawable.round_corner)
            .into(holder.bannerImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.api1_image_item, parent, false)
        return ViewHolder(view)
    }
}