package com.shawn.githubdemo.domain.user

import com.shawn.githubdemo.model.dto.user.UserResponse
import com.shawn.githubdemo.model.source.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMineUserUseCase@Inject constructor(
    private val repository:UserRepository
) {
    suspend operator fun invoke(): Flow<UserResponse> {
        return repository.getUser().map {
            if(it.email.isNullOrEmpty()){
                it.copy(email = "No Email")
            }else{
                it
            }
        }
    }
}