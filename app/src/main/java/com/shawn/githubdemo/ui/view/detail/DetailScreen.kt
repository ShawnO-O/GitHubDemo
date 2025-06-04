package com.shawn.githubdemo.ui.view.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.layoutId
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.shawn.githubdemo.model.dto.user.UserResponse
import com.shawn.githubdemo.ui.widget.IconText

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(profileInfo: UserResponse) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize(1f)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, "", modifier = Modifier.padding(16.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxWidth(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImage(
                    model = profileInfo.avatar_url,
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                        .layoutId("avatar")
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = profileInfo.login, fontSize = 20.sp)
            }
            HorizontalDivider(thickness = 1.dp, color = Color.Red,modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ){
                IconText(Icons.Default.Person, text = profileInfo.name.toString())
                IconText(Icons.Default.LocationOn, text = profileInfo.location.toString())
                IconText(Icons.Default.Home, text = profileInfo.reposUrl)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun test(){
    DetailScreen(UserResponse(login = "Shawn0.0"))
}