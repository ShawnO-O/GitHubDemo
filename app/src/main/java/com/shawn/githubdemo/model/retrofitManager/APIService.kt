package com.shawn.githubdemo.model.retrofitManager

import com.shawn.githubdemo.model.dto.list.ListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIService {
    @Headers("Authorization: Bearer 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")
    @GET("search/code")
    suspend fun getSearchList(
        @Query("q") q: String,
        @Query("perPage") perPage: String,
        @Query("page") page: String,
    ): Response<ListResponse>
}