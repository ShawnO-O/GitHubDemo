package com.shawn.githubdemo.model.source.remote.list

import com.shawn.githubdemo.model.dto.repoList.RepoListRequest
import com.shawn.githubdemo.model.dto.repoList.RepoListResponse
import com.shawn.githubdemo.model.retrofitManager.APIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListRemoteDataSource @Inject constructor(private val apiService: APIService) {
    fun getFirstPageList(
      request: RepoListRequest
    ): Flow<RepoListResponse> = flow{
        val token = "Bearer"

        val response = apiService.getSearchList(token,request.q, request.page.toString(), request.perPage.toString())
        if(response.isSuccessful){
            val listResponse = response.body()
            if(listResponse != null){
                emit(listResponse)
            }else{
                throw Exception("ListResponse is null")
            }
        }else{
            throw Exception("Error:${response.message()}")
        }
    }
}