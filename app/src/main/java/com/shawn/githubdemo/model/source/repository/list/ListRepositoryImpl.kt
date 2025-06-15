package com.shawn.githubdemo.model.source.repository.list

import SearchListResponse
import com.shawn.githubdemo.model.dto.search.SearchListRequest
import com.shawn.githubdemo.model.source.remote.list.ListRemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class ListRepositoryImpl @Inject constructor(private var listRemoteDataSource: ListRemoteDataSource) : ListRepository {
    override fun getSearchList(request: SearchListRequest): Flow<SearchListResponse> {
       return listRemoteDataSource.getFirstPageList(request)
    }
}