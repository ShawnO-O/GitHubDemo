package com.shawn.githubdemo.model.source.repository.list

import SearchListResponse
import com.shawn.githubdemo.model.dto.search.SearchListRequest
import kotlinx.coroutines.flow.Flow

fun interface ListRepository {
    fun getSearchList(request:SearchListRequest): Flow<SearchListResponse>
}