package com.shawn.githubdemo.model.source.remote.user

import android.util.Log
import com.shawn.githubdemo.GitHubDemoApplication
import com.shawn.githubdemo.model.dto.user.UserResponse
import com.shawn.githubdemo.model.retrofitManager.APIService
import com.shawn.githubdemo.utils.getTokenFromAssets
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRemoteDataSource@Inject constructor(private val apiService: APIService) {
    fun getMineUser(): Flow<UserResponse> = flow {
        val response = apiService.getUser("Bearer "+getTokenFromAssets(GitHubDemoApplication.applicationContext()))
        if (response.isSuccessful) {
            val userResponse = response.body()
            if (userResponse != null) {
                emit(userResponse)
            } else {
                throw Exception("UserResponse is null")
            }
        } else {
            throw Exception("Error:${response.message()}")
        }
    }
}