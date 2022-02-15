package com.vikas.zoodmall.mobile.network.model.api2

import com.google.gson.annotations.SerializedName

data class Api2Model(
        @field:SerializedName("Data") val data: Data,
        val ErrorCode: String,
        val Message: String
) {
    data class Data(
        val pagination: Pagination,
        val marketList: List<Market>
    ) {
        data class Pagination(
            val page: Int,
            val rowsPerPage: Int,
            val totalCount: Int,
            val totalPage: Int
        )

        data class Market(
            val brand: String,
            val imgUrl: String,
            val localCrossedPrice: Int,
            val localPrice: Int,
            val name: String,
            val productId: Int,
            val rank: Int,
            val ratingEmoji: String
        )
    }
}