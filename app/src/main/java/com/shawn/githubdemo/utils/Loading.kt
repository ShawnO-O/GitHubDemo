package com.shawn.githubdemo.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FullPageLoading(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun LoadingFooter(){
    Box(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(16.dp), // 給 Footer 一些內邊距
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material.CircularProgressIndicator()
    }
}

@Composable
fun BottomOfList(){

}