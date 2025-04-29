package com.shawn.githubdemo.model.source.repository.list

import com.shawn.githubdemo.model.dto.list.ListRequest
import com.shawn.githubdemo.model.dto.list.RepositoriesListResponse
import kotlinx.coroutines.flow.Flow

interface ListRepository {
    fun getFirstPageList(request:ListRequest): Flow<RepositoriesListResponse>

    fun getNextPageList(request:ListRequest): Flow<RepositoriesListResponse>
}