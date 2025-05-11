package com.shawn.githubdemo.domain.repoList

import com.shawn.githubdemo.model.dto.repoList.RepoListRequest
import com.shawn.githubdemo.model.dto.repoList.RepoListResponse
import com.shawn.githubdemo.model.source.repository.repoList.RepoListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepoListUseCase @Inject constructor(
    private val repository: RepoListRepository
){
    suspend operator fun invoke(query: String, page: Int): Flow<RepoListResponse> {
        return repository.getRepoPageList(RepoListRequest(query, page))
    }
}