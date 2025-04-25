package com.shawn.githubdemo.ui

import androidx.lifecycle.ViewModelProvider
import com.shawn.githubdemo.model.source.repository.list.ListRepositoryImpl
import com.shawn.githubdemo.ui.view.list.ListViewModel

class ListViewModelFactory(private val listRepositoryImpl: ListRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(listRepositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}