package com.shawn.githubdemo.ui.view.repoList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.shawn.githubdemo.R
import com.shawn.githubdemo.model.dto.repoList.RepoListItem

@Composable
fun ListScreen(
    listViewModel: RepoListViewModel = hiltViewModel()
) {
    listViewModel.getFirstPageList("kotlin")
    val repos = listViewModel.listData.collectAsState()
    Scaffold { innerPadding ->
        ContentList(
            repos.value,
            innerPadding,
            listViewModel
        )
    }
}


@Composable
fun ContentList(
    list: List<RepoListItem>,
    innerPadding: PaddingValues,
    listViewModel: RepoListViewModel
) {
        val buffer = 3  // load more when scroll reaches last n item, where n >= 1
        val listState = rememberLazyListState()
        val reachedBottom: Boolean by remember {
            derivedStateOf {
                val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
                lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - buffer
            }
        }
        LaunchedEffect(reachedBottom) {
            if (reachedBottom) {
                listViewModel.getNextPageList()
            }
        }
        LazyColumn(
            state = listState,
            modifier = Modifier.padding(innerPadding)
        ) {
            items(list) { item ->
                ListItem(item)
            }
        }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ListItem(repoListItem:RepoListItem) {
    repoListItem.apply {
        Card(
            modifier = Modifier
                .background(Color.Transparent)
                .padding(10.dp),
            border = BorderStroke(1.dp, colorResource(id = R.color.teal_300))
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GlideImage(
                        model = owner.avatarUrl,
                        contentDescription = "",
                        modifier = Modifier.padding(4.dp).clip(RoundedCornerShape(160.dp)).size(50.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp),
                    ) {
                        Text(text = name, fontSize = 16.sp)
                        Text(text = owner.login, fontSize = 12.sp)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        fontSize = 10.sp,
                        text = updatedAt,
                        textAlign = TextAlign.End
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = colorResource(id = R.color.gray_400),
                    thickness = 1.dp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.watcher),
                            contentDescription = ""
                        )
                        Text(text = stargazersCount.toString(), fontSize = 12.sp)
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = ""
                        )
                        Text(text = watchersCount.toString(), fontSize = 12.sp)
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.fork),
                            contentDescription = ""
                        )
                        Text(text = forksCount.toString(), fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

