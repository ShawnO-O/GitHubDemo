package com.shawn.githubdemo.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun LoadingDialog(
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    if(isLoading){
        Dialog(onDismissRequest={ }){
           Column(
               modifier = modifier
                   .background(Color.White, shape = RoundedCornerShape(8.dp))
                   .padding(16.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement =  Arrangement.spacedBy(8.dp)
           ) {
               CircularProgressIndicator(
                   modifier = Modifier.size(48.dp),
                   color = MaterialTheme.colorScheme.primary
               )
               Text(text = "Loading...")
           }
        }
    }
}