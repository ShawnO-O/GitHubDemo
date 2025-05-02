package com.shawn.githubdemo.model.source.repository.repoList

import com.shawn.githubdemo.model.dto.repoList.RepoListRequest
import com.shawn.githubdemo.model.dto.repoList.RepoListResponse
import kotlinx.coroutines.flow.Flow

interface RepoListRepository {
    fun getFirstPageList(request:RepoListRequest): Flow<RepoListResponse>

    fun getNextPageList(request:RepoListRequest): Flow<RepoListResponse>
}