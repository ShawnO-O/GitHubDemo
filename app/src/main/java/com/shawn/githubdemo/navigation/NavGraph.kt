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
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shawn.githubdemo.ui.view.UserInfo.MineInfoScreen
import com.shawn.githubdemo.ui.view.detail.DetailScreen
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
        composable(BottomNavItem.UserDetail){
            DetailScreen()
        }
    }
}

@Composable
fun SwipeableNavigation(navController: NavHostController) {
    val pages = listOf(
        BottomNavItem.Repo.route,
        BottomNavItem.User.route,
        BottomNavItem.Mine.route
    )
    val pagerState = rememberPagerState(pageCount = { pages.size })

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage){
        val route = pages[pagerState.currentPage]
        if(navController.currentDestination?.route != route){
            navController.navigate(route){
                popUpTo(navController.graph.startDestinationId){
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
    LaunchedEffect(navController.currentDestination?.route){
        val currentRoute = navController.currentDestination?.route
        val currentPage = pages.indexOf(currentRoute)
        if(currentPage != -1 && currentPage != pagerState.currentPage){
            coroutineScope.launch {
                pagerState.animateScrollToPage(currentPage)
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()){
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ){ page ->
            NavHost(
                navController = navController,
                startDestination = BottomNavItem.Repo.route,
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
    }
}