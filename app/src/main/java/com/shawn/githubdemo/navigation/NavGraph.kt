package com.shawn.githubdemo.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shawn.githubdemo.ui.view.mineInfo.MineInfoScreen
import com.shawn.githubdemo.ui.view.repoList.RepoListScreen
import com.shawn.githubdemo.ui.view.userList.UserListScreen

@Composable
fun NavigationConfigurations(
    navController:NavHostController,
    innerPadding:PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Repo.route,
        modifier = Modifier.padding(innerPadding)
    ){
        composable(BottomNavItem.Repo.route){
            RepoListScreen()
        }
        composable(BottomNavItem.User.route){
            UserListScreen()
        }
        composable(BottomNavItem.Mine.route){
            MineInfoScreen()
        }
    }
}