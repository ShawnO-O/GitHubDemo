package com.shawn.githubdemo.model.dto.list

data class ListRequest(
    val q: String,
    val page: Int = 1,
    val perPage: Int = 30
)