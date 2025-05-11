package com.shawn.githubdemo.ui.view.UserInfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MineInfoScreen() {
    Scaffold{ innerPadding->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(text = "MineInfoScreen")
        }
    }
}