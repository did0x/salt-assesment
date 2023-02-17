package com.putrash.data

import com.putrash.data.response.ResultData
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("category") category: String = "",
        @Query("apiKey") apiKey: String
    ): ResultData
}