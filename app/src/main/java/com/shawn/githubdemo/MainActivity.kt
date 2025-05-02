package com.shawn.githubdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shawn.githubdemo.model.retrofitManager.RetrofitManager
import com.shawn.githubdemo.model.sealeds.UiState
import com.shawn.githubdemo.model.source.remote.list.ListRemoteDataSource
import com.shawn.githubdemo.model.source.repository.repoList.RepoListRepositoryImpl
import com.shawn.githubdemo.ui.ListViewModelFactory
import com.shawn.githubdemo.ui.theme.GitHubDemoTheme
import com.shawn.githubdemo.ui.view.repoList.ListScreen
import com.shawn.githubdemo.ui.view.repoList.RepoListViewModel
import com.shawn.githubdemo.utils.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val listViewModel: RepoListViewModel by viewModels{ListViewModelFactory(RepoListRepositoryImpl(
        ListRemoteDataSource(RetrofitManager.getApiService())
    ))}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        listViewModel.getFirstPageList("kotlin")
       CoroutineScope(Dispatchers.Main).launch{
           listViewModel.apply {
               listData.collect {
                   Log.d("shawnTest","listData:$it")
                   println(it)
               }
           }
       }
        CoroutineScope(Dispatchers.Main).launch{
            listViewModel.apply {
                uiState.collect {
                    when(it){
                        is UiState.Loading -> Log.d("shawnTest","Loading")
                        is UiState.Success -> Log.d("shawnTest","Success")
                        is UiState.Error -> it.message.showToast()
                        is UiState.Empty -> it.message.showToast()
                        else -> Log.d("shawnTest","Unknown")
                    }
                    Log.d("shawnTest","uiState:$it")
                    println(it)
                }
            }
        }
        setContent {
            GitHubDemoTheme {
                ListScreen(listViewModel)
            }
        }
    }
}