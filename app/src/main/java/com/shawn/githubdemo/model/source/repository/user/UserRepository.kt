package com.shawn.githubdemo.model.source.repository.user

import com.shawn.githubdemo.model.dto.user.UserResponse
import kotlinx.coroutines.flow.Flow

fun interface UserRepository {
    fun getUser(): Flow<UserResponse>
}