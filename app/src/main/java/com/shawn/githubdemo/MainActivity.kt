package com.shawn.githubdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shawn.githubdemo.navigation.BottomNavItem
import com.shawn.githubdemo.navigation.NavigationConfigurations
import com.shawn.githubdemo.ui.theme.GitHubDemoTheme
import com.shawn.githubdemo.ui.view.repoList.RepoListScreen
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
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomNavigationItems = listOf(BottomNavItem.Repo, BottomNavItem.User, BottomNavItem.Mine)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        bottomBar = {
            BottomNavigationBar(navController = navController, items = bottomNavigationItems)
        }
    ) { innerPadding ->
        NavigationConfigurations(navController, innerPadding)
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, items: List<BottomNavItem>) {

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.teal_300)
    ) {
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntry?.destination

        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = null) },
                selected = currentDestination?.route == screen.route,
                onClick = {
                    if (currentDestination?.route != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                label = { Text(text = screen.label) },
                alwaysShowLabel = false,
            )

        }
    }
}