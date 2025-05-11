package com.shawn.githubdemo.model.source.repository.repoList

import com.shawn.githubdemo.model.dto.repoList.RepoListRequest
import com.shawn.githubdemo.model.dto.repoList.RepoListResponse
import kotlinx.coroutines.flow.Flow

fun interface RepoListRepository {
    fun getRepoPageList(request:RepoListRequest): Flow<RepoListResponse>
}