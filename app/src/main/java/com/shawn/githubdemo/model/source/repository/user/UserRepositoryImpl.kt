package com.shawn.githubdemo.model.source.repository.user

import android.util.Log
import com.shawn.githubdemo.model.dto.user.UserResponse
import com.shawn.githubdemo.model.source.remote.user.UserRemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class UserRepositoryImpl @Inject constructor(private var userRemoteDataSource: UserRemoteDataSource) :UserRepository {
    override fun getUser(): Flow<UserResponse> {
            return userRemoteDataSource.getMineUser()
    }
}