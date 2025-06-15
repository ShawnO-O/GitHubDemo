package com.shawn.githubdemo.ui.view.userList

import SearchListItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shawn.githubdemo.GitHubDemoApplication
import com.shawn.githubdemo.R
import com.shawn.githubdemo.domain.repoList.GetSearchListUseCase
import com.shawn.githubdemo.model.sealeds.UiState
import com.shawn.githubdemo.model.sealeds.UiState.FirstEmpty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getSearchListUseCase: GetSearchListUseCase
):ViewModel()  {
    private val _uiState = MutableStateFlow<UiState>(
        FirstEmpty(
            GitHubDemoApplication.applicationContext().getString(
                R.string.searchByKeyword))
    )
    val uiState: StateFlow<UiState> = _uiState
    private val _listData = MutableStateFlow<List<SearchListItem>>(emptyList())
    val listData: StateFlow<List<SearchListItem>> = _listData
    private var currentPage = 1
    private var q: String? = null

    fun getFirstPageList(q: String?) {
//        _uiState.value = UiState.LoadingNotFirst
        q?.let {
            this.q = q
            viewModelScope.launch {
                _uiState.value = UiState.LoadingNotFirst
                currentPage = 1
                getSearchListUseCase(it, currentPage)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.message ?: "Unknown error")
                    }.collectLatest {
                        _listData.value = it.items
                        _uiState.value = UiState.Success
                    }
            }
        } ?: run {
            _uiState.value = UiState.Error("Query is null")
        }
    }

    fun getNextPageList() {
        q?.let {
            viewModelScope.launch {
                _uiState.value = UiState.LoadingNotFirst
                currentPage++
                getSearchListUseCase(it, currentPage)
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