package com.shawn.githubdemo.model.sealeds

import com.shawn.githubdemo.model.dto.list.ListResponse

sealed class UiState {
    data object Loading : UiState()
    data object Success : UiState()
    data class Error(val message: String) : UiState()
    data class Empty(val message: String) : UiState()
    data class NoNetwork(val message: String) : UiState()
}