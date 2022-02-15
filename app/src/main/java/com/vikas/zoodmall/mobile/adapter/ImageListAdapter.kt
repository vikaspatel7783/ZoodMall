package com.vikas.zoodmall.mobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vikas.zoodmall.mobile.R
import com.vikas.zoodmall.mobile.view.ApiUIModel

class ImageListAdapter(private val dataSet: List<ApiUIModel>,
                       private val onClick: (ApiUIModel) -> Unit) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bannerImage: ImageView = view.findViewById(R.id.glide_image)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.api1_image_item, viewGroup, false)
        val viewHolder = ViewHolder(view)

        viewHolder.bannerImage.setOnClickListener {
            onClick(it.tag as ApiUIModel)
        }

        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val banner = dataSet[position]
        viewHolder.bannerImage.tag = banner

        Glide.with(viewHolder.itemView.context)
            .load(banner.imageUrl)
            .placeholder(R.drawable.round_corner)
            .into(viewHolder.bannerImage)


    }

    override fun getItemCount() = dataSet.size
}
