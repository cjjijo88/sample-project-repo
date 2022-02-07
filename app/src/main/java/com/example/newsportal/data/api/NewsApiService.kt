package com.example.newsportal.data.api

import com.example.newsportal.data.model.BaseData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author: Jijo
 * @Date: 06-02-2022
 */
interface NewsApiService {
    @GET("v2/everything")
    fun getNewsData(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): Observable<BaseData>
}