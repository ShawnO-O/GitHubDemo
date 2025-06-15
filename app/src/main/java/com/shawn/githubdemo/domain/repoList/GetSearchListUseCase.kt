package com.shawn.githubdemo.domain.repoList

import SearchListResponse
import com.shawn.githubdemo.model.dto.search.SearchListRequest
import com.shawn.githubdemo.model.source.repository.list.ListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchListUseCase @Inject constructor(
    private val repository: ListRepository
){
    suspend operator fun invoke(query: String, page: Int): Flow<SearchListResponse> {
        return repository.getSearchList(SearchListRequest(query, page))
    }
}