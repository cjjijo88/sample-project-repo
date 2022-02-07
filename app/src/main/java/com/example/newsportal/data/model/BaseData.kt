package com.example.newsportal.data.model

import com.google.gson.annotations.SerializedName

/**
 * @Author: Jijo
 * @Date: 05-02-2022
 */
data class BaseData(
    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticleData>? = null,

    @field:SerializedName("status")
    val status: String? = null
)