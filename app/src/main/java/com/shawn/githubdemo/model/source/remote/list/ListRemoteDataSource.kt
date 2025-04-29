package com.shawn.githubdemo.model.source.remote.list

import android.util.Log
import com.shawn.githubdemo.model.dto.list.ListRequest
import com.shawn.githubdemo.model.dto.list.RepositoriesListResponse
import com.shawn.githubdemo.model.retrofitManager.APIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ListRemoteDataSource(private val apiService: APIService) {
    fun getFirstPageList(
      request: ListRequest
    ): Flow<RepositoriesListResponse> = flow{
        val token = "Bearer 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"
        val response = apiService.getSearchList(token,request.q, request.perPage.toString(), request.page.toString())
        if(response.isSuccessful){
            val listResponse = response.body()
            Log.d("shawnTest","listResponse:$listResponse")
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