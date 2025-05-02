package com.shawn.githubdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.shawn.githubdemo.ui.theme.GitHubDemoTheme
import com.shawn.githubdemo.ui.view.repoList.ListScreen
import com.shawn.githubdemo.ui.view.repoList.RepoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val listViewModel: RepoListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GitHubDemoTheme {
                ListScreen(listViewModel)
            }
        }
    }
}