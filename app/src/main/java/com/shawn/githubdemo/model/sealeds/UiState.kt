package com.shawn.githubdemo.model.sealeds

sealed class UiState {
    data object LoadingFirst : UiState()
    data object LoadingNotFirst : UiState()
    data object Success : UiState()
    data class Error(val message: String) : UiState()
    data class Empty(val message: String) : UiState()
    data class NoNetwork(val message: String) : UiState()
}