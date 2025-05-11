package com.shawn.githubdemo.ui.view.profileInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shawn.githubdemo.domain.user.GetMineUserUseCase
import com.shawn.githubdemo.model.dto.user.UserResponse
import com.shawn.githubdemo.model.sealeds.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileInfoViewModel @Inject constructor(
    private val getMineUserUseCase: GetMineUserUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.LoadingFirst)
    val uiState: StateFlow<UiState> = _uiState
    private val _user = MutableStateFlow<UserResponse>(UserResponse())
    val user: StateFlow<UserResponse> = _user
    fun getMineUser() {
        viewModelScope.launch {
            _uiState.value = UiState.LoadingNotFirst
            getMineUserUseCase().catch { e ->
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }.collectLatest {
                _user.value = it
                _uiState.value = UiState.Success
            }
        }
    }
}