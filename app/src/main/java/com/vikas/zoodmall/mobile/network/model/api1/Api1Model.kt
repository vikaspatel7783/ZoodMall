package com.vikas.zoodmall.mobile.network.model.api1

import com.google.gson.annotations.SerializedName

data class Api1Model(
        @field:SerializedName("Data") val data: Data,
        val ErrorCode: String,
        val Message: String
) {
    data class Data(
        val brandZoneBanner: List<BrandZoneBanner>,
        val mainBanner: List<MainBanner>,
        val promotionalBanner: List<PromotionalBanner>,
        val promotionalBanner2: List<PromotionalBanner2>,
        val recommended: Recommended
    ) {
        data class BrandZoneBanner(
            val id: Int,
            val imageUrl: String
        )

        data class MainBanner(
            val id: Int,
            val imageUrl: String
        )

        data class PromotionalBanner(
            val id: Int,
            val imageUrl: String
        )

        data class PromotionalBanner2(
            val id: Int,
            val imageUrl: String
        )

        data class Recommended(
            val name: String,
            val productTagId: Int
        )
    }
}