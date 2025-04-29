package com.shawn.githubdemo.ui.view.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shawn.githubdemo.model.dto.list.RepositoriesListItem
import com.shawn.githubdemo.model.dto.list.ListRequest
import com.shawn.githubdemo.model.sealeds.UiState
import com.shawn.githubdemo.model.source.repository.list.ListRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListViewModel(private var listRepositoryImpl: ListRepositoryImpl) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState
    private val _listData = MutableStateFlow<List<RepositoriesListItem>>(emptyList())
    val listData : StateFlow<List<RepositoriesListItem>> = _listData
    private var currentPage = 1
    private var q: String? = null

    fun getFirstPageList(q: String?) {
        q?.let {
            viewModelScope.launch {
                _uiState.value = UiState.Loading
                currentPage = 1
                listRepositoryImpl.getFirstPageList(ListRequest(q = it, page = currentPage))
                    .collect {
                        Log.d("shawnTest","getFirstPageList: ${it.total_count}")
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
                listRepositoryImpl.getNextPageList(ListRequest(q = it, page = currentPage))
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