package com.shawn.githubdemo.domain.repoList

import com.shawn.githubdemo.model.dto.repoList.RepoListItem
import com.shawn.githubdemo.model.source.repository.repoList.RepoListRepository
import javax.inject.Inject

class GetNextRepoListUseCase@Inject constructor(
    private val repoListRepository: RepoListRepository
) {

}