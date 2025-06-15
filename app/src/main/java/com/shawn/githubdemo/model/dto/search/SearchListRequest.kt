package com.shawn.githubdemo.model.dto.search

data class SearchListRequest(
    val q: String,
    val page: Int = 1,
    val perPage: Int = 30
)