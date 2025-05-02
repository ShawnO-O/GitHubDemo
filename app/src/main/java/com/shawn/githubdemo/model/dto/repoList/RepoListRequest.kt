package com.shawn.githubdemo.model.dto.repoList

data class RepoListRequest(
    val q: String,
    val page: Int = 1,
    val perPage: Int = 30
)