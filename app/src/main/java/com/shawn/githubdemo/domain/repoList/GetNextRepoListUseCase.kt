package com.shawn.githubdemo.domain.repoList

import com.shawn.githubdemo.model.source.repository.list.ListRepository
import javax.inject.Inject

class GetNextRepoListUseCase@Inject constructor(
    private val listRepository: ListRepository
) {

}