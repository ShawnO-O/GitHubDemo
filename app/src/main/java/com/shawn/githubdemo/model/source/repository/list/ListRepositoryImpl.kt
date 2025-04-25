package com.shawn.githubdemo.model.source.repository.list

import com.shawn.githubdemo.model.dto.list.ListRequest
import com.shawn.githubdemo.model.dto.list.ListResponse
import com.shawn.githubdemo.model.source.remote.list.ListRemoteDataSource
import kotlinx.coroutines.flow.Flow

class ListRepositoryImpl(private var listRemoteDataSource: ListRemoteDataSource) : ListRepository {
    override fun getFirstPageList( request: ListRequest): Flow<ListResponse> {
       return listRemoteDataSource.getFirstPageList(request)
    }

    override fun getNextPageList( request: ListRequest): Flow<ListResponse> {
        return listRemoteDataSource.getFirstPageList(request)
    }
}