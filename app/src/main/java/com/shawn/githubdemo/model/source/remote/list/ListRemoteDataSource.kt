package com.shawn.githubdemo.model.source.remote.list

import com.shawn.githubdemo.model.dto.list.ListRequest
import com.shawn.githubdemo.model.dto.list.ListResponse
import com.shawn.githubdemo.model.retrofitManager.APIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ListRemoteDataSource(private val apiService: APIService) {
    fun getFirstPageList(
      request: ListRequest
    ): Flow<ListResponse> = flow{
        val response = apiService.getSearchList(request.q, request.perPage.toString(), request.page.toString())
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