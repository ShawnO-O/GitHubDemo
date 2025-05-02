package com.shawn.githubdemo.ui

import androidx.lifecycle.ViewModelProvider
import com.shawn.githubdemo.model.source.repository.repoList.RepoListRepositoryImpl
import com.shawn.githubdemo.ui.view.repoList.RepoListViewModel

class ListViewModelFactory(private val listRepositoryImpl: RepoListRepositoryImpl): ViewModelProvider.Factory {
    //使用hilt這邊就會消失了
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoListViewModel::class.java)) {
            return RepoListViewModel(listRepositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}