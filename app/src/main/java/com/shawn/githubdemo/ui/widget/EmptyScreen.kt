package com.shawn.githubdemo.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.shawn.githubdemo.R

@Composable
fun EmptyScreen(message:String) {
    Scaffold(

    ) { innerPadding->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(innerPadding).fillMaxSize(1f)
        ) {
            //從drawable取出圖片
            Image(painter = painterResource(id = R.drawable.ic_search_init), contentDescription = "")
            Text(text = message)
        }
    }
}