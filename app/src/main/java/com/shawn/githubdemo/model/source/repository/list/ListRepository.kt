package com.shawn.githubdemo.model.source.repository.list

import com.shawn.githubdemo.model.dto.list.ListRequest
import com.shawn.githubdemo.model.dto.list.ListResponse
import kotlinx.coroutines.flow.Flow

interface ListRepository {
    fun getFirstPageList(request:ListRequest): Flow<ListResponse>

    fun getNextPageList(request:ListRequest): Flow<ListResponse>
}