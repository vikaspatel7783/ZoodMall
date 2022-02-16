package com.vikas.zoodmall.mobile.adapter

import androidx.recyclerview.widget.DiffUtil
import com.vikas.zoodmall.mobile.view.ApiUIModel

object ImageDataComparator : DiffUtil.ItemCallback<ApiUIModel>() {

    override fun areItemsTheSame(oldItem: ApiUIModel, newItem: ApiUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ApiUIModel, newItem: ApiUIModel): Boolean {
        return oldItem == newItem
    }
}