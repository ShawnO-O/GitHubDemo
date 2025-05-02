package com.shawn.githubdemo.ui.view.repoList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shawn.githubdemo.model.dto.repoList.RepoListItem
import com.shawn.githubdemo.model.dto.repoList.RepoListRequest
import com.shawn.githubdemo.model.sealeds.UiState
import com.shawn.githubdemo.model.source.repository.repoList.RepoListRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RepoListViewModel(private var listRepositoryImpl: RepoListRepositoryImpl) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState
    private val _listData = MutableStateFlow<List<RepoListItem>>(emptyList())
    val listData : StateFlow<List<RepoListItem>> = _listData
    private var currentPage = 1
    private var q: String? = null

    fun getFirstPageList(q: String?) {
        q?.let {
            viewModelScope.launch {
                _uiState.value = UiState.Loading
                currentPage = 1
                listRepositoryImpl.getFirstPageList(RepoListRequest(q = it, page = currentPage))
                    .collect {
                        Log.d("shawnTest","getFirstPageList: ${it.totalCount}")
                        _listData.value = it.items
                        _uiState.value = UiState.Success

                    }

            }
        }?: run {
            _uiState.value = UiState.Error("Query is null")
        }
    }

    fun getNextPageList() {
        q?.let {
            viewModelScope.launch {
                _uiState.value = UiState.Loading
                currentPage++
                listRepositoryImpl.getNextPageList(RepoListRequest(q = it, page = currentPage))
                    .collect {
                        if(it.items.isNotEmpty()) {
                            _listData.value += it.items
                            _uiState.value = UiState.Success
                        }else{
                            _uiState.value = UiState.Empty("No more data")
                        }
                    }
            }
        } ?: run {
            _uiState.value = UiState.Error("Query is null")
        }
    }
}