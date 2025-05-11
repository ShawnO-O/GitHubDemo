package com.shawn.githubdemo.model.source.remote.user

import android.util.Log
import com.shawn.githubdemo.model.dto.user.UserResponse
import com.shawn.githubdemo.model.retrofitManager.APIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRemoteDataSource@Inject constructor(private val apiService: APIService) {
    fun getMineUser(): Flow<UserResponse> = flow {
        val token =
            "Bearer "

        val response = apiService.getUser(token)
        if (response.isSuccessful) {
            val userResponse = response.body()
            Log.d("shawnTest","$userResponse")
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