package com.shawn.githubdemo.model.retrofitManager

import com.shawn.githubdemo.model.dto.repoList.RepoListResponse
import com.shawn.githubdemo.model.dto.user.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface APIService {
    @GET("search/repositories")
    suspend fun getSearchList(
        @Header("Authorization") token: String,
        @Query("q") q: String,
        @Query("page") page: String,
        @Query("perPage") perPage: String,
    ): Response<RepoListResponse>

    @GET("user")
    suspend fun getUser(
        @Header("Authorization") token: String,
    ): Response<UserResponse>

}