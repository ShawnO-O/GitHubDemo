package com.shawn.githubdemo.ui.view.profileInfo

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.shawn.githubdemo.R
import com.shawn.githubdemo.model.dto.user.UserResponse
import com.shawn.githubdemo.model.sealeds.UiState
import com.shawn.githubdemo.utils.FullPageLoading

@Composable
fun ProfileScreen(
    viewModel: ProfileInfoViewModel = hiltViewModel()
) {
    viewModel.getMineUser()
    Scaffold { innerPadding ->
        ProfileContent(innerPadding, viewModel)
    }
}


@Composable
fun ProfileContent(
    innerPadding: PaddingValues,
    viewModel: ProfileInfoViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val profileInfo = viewModel.user.collectAsState()

    when (uiState) {
        is UiState.LoadingFirst -> {
            FullPageLoading()
        }

        is UiState.LoadingNotFirst -> {

        }

        is UiState.Empty -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (uiState as UiState.Empty).message)
            }
        }

        is UiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (uiState as UiState.Error).message)
            }
        }

        is UiState.Success -> {
            ProfilePage(profileInfo.value)
        }

        is UiState.NoNetwork -> TODO()
        is UiState.FirstEmpty -> TODO()
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfilePage(profileInfo: UserResponse) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.teal_300))

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(R.drawable.ic_github),
                    contentDescription = null,
                    modifier = Modifier.scale(0.5f)
                    )
                Text(
                    text = "GitHubDemo",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        //頭像與基本資料
        // 頭像與基本資料
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Log.d("shawnTest","${profileInfo.avatar_url}")
            GlideImage(
                model = profileInfo.avatar_url,
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(profileInfo.login, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(profileInfo.type, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = Color.Gray)
                Text(
                    " ${profileInfo.followers} followers · ${profileInfo.following} following",
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Email, contentDescription = null, tint = Color.Gray)
                Text(
                    text = if(profileInfo.email.isEmpty()) "No Email" else profileInfo.email, color = Color.Gray)
            }
        }
    }
}