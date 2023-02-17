package com.putrash.data.response

import com.google.gson.annotations.SerializedName
import com.putrash.data.model.Article

data class ResultData(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("code")
    val code: String = "",
    @SerializedName("message")
    val message: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0,
    @SerializedName("articles")
    val articles: ArrayList<Article>
)
