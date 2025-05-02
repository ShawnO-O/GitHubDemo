package com.shawn.githubdemo.model.source.repository.repoList

import com.shawn.githubdemo.model.dto.repoList.RepoListRequest
import com.shawn.githubdemo.model.dto.repoList.RepoListResponse
import com.shawn.githubdemo.model.source.remote.list.ListRemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class RepoListRepositoryImpl @Inject constructor(private var listRemoteDataSource: ListRemoteDataSource) : RepoListRepository {
    override fun getFirstPageList( request: RepoListRequest): Flow<RepoListResponse> {
       return listRemoteDataSource.getFirstPageList(request)
    }

    override fun getNextPageList( request: RepoListRequest): Flow<RepoListResponse> {
        return listRemoteDataSource.getFirstPageList(request)
    }
}