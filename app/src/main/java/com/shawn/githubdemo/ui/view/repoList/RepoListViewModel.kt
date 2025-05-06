package com.shawn.githubdemo.ui.view.repoList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shawn.githubdemo.domain.repoList.GetRepoListUseCase
import com.shawn.githubdemo.model.dto.repoList.RepoListItem
import com.shawn.githubdemo.model.sealeds.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val getRepoListUseCase: GetRepoListUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState
    private val _listData = MutableStateFlow<List<RepoListItem>>(emptyList())
    val listData: StateFlow<List<RepoListItem>> = _listData
    private var currentPage = 1
    private var q: String? = null

    fun getFirstPageList(q: String?) {
        q?.let {
            this.q = q
            viewModelScope.launch {
                _uiState.value = UiState.Loading
                currentPage = 1
                getRepoListUseCase(it, currentPage)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.message ?: "Unknown error")
                    }.collectLatest {
                        _listData.value = it.items
                        _uiState.value = UiState.Success
                        Log.d("shawnTest", "list:${it.items}")
                    }
            }
        } ?: run {
            _uiState.value = UiState.Error("Query is null")
        }
    }

    fun getNextPageList() {
        Log.d("shawnTest", "getNextPageList")
        q?.let {
            viewModelScope.launch {
                _uiState.value = UiState.Loading
                currentPage++
                getRepoListUseCase(it, currentPage)
                    .catch { e->
                        _uiState.value = UiState.Error(e.message ?: "Unknown error")
                    }.collectLatest {
                        if (it.items.isNotEmpty()) {
                            _listData.value += it.items
                            _uiState.value = UiState.Success
                        } else {
                            _uiState.value = UiState.Empty("No more data")
                        }
                    }
            }
        }?: run{
            _uiState.value = UiState.Error("Query is null")
        }
    }
}