package com.vikas.zoodmall.mobile.network

import com.vikas.zoodmall.mobile.network.model.api1.Api1Model
import com.vikas.zoodmall.mobile.network.model.api2.Api2Model
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ZoodMallService {

    @GET("home?marketCode=UZ")
    suspend fun callApi1(): Api1Model

    @GET("productlist")
    suspend fun callApi2(
        @Query("page") page: Int = 1,
        @Query("productTagId") productTagId: String = "13",
        @Query("marketCode") marketCode: String = "UZ"): Api2Model

    companion object {
        private const val BASE_URL = "http://qvr9g.mocklab.io/"

        fun create(): ZoodMallService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ZoodMallService::class.java)
        }
    }
}