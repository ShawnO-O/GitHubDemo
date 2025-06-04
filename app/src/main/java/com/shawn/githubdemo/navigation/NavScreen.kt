package com.shawn.githubdemo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route:String,val icon:ImageVector,val label:String){
    data object Repo :BottomNavItem("repo", Icons.Default.Search,"Repo")
    data object User :BottomNavItem("user", Icons.Default.Person,"User")
    data object Mine :BottomNavItem("Mine", Icons.Default.Info,"Mine")
    data object UserDetail :BottomNavItem("userDetail", Icons.Default.Info,"UserDetail")
}