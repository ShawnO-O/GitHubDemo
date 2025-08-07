package com.shawn.githubdemo.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shawn.githubdemo.ui.view.profileInfo.ProfileScreen
import com.shawn.githubdemo.ui.view.repoList.RepoListScreen
import com.shawn.githubdemo.ui.view.userList.UserListScreen
import kotlinx.coroutines.launch

@Composable
fun NavigationConfigurations(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Repo.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(BottomNavItem.Repo.route) {
            RepoListScreen()
        }
        composable(BottomNavItem.User.route) {
            UserListScreen()
        }
        composable(BottomNavItem.Mine.route) {
            ProfileScreen()
        }
    }
}
