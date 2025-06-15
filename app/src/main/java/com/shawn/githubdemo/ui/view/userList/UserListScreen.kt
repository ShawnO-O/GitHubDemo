package com.shawn.githubdemo.ui.view.userList

import Owner
import SearchListItem
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.shawn.githubdemo.R
import com.shawn.githubdemo.model.sealeds.UiState
import com.shawn.githubdemo.ui.widget.EmptyScreen
import com.shawn.githubdemo.utils.FullPageLoading
import com.shawn.githubdemo.utils.LoadingFooter

@Composable
fun UserListScreen(
    listViewModel: UserListViewModel = hiltViewModel()
) {
    var tfKeyword by remember { mutableStateOf("") }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp),
                value = tfKeyword,
                label = {
                    Text(stringResource(R.string.searchByKeyword))
                },
                onValueChange = { newValue ->
                    tfKeyword = newValue
                    if (newValue.length >= 2) {
                        listViewModel.getFirstPageList(newValue)
                    }
                },
            )
            //這邊還缺一個歷史紀錄，搭配room的好時機
            ContentList(
                listViewModel
            )
        }
    }
}


@Composable
fun ContentList(
    listViewModel: UserListViewModel
) {
    val buffer = 3  // load more when scroll reaches last n item, where n >= 1
    val listState = rememberLazyListState()
    val uiState by listViewModel.uiState.collectAsState()
    val repos = listViewModel.listData.collectAsState()
    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            //因為改成每個cell 兩張卡片所以要/2
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - (buffer / 2)
        }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) {
            listViewModel.getNextPageList()
        }
    }

    when (uiState) {
        is UiState.FirstEmpty -> {
            EmptyScreen(stringResource(R.string.searchByKeyword))
        }

        is UiState.LoadingFirst -> {
            FullPageLoading()
        }

        is UiState.LoadingNotFirst -> {
            Log.d("shawnTest","repos.value.isNotEmpty():"+repos.value.isNotEmpty())
            if(repos.value.isNotEmpty()){
                DisplayRepoList(listState,repos.value,isLoadingMore = true)
            }else{
                FullPageLoading()
            }
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
            DisplayRepoList(listState, repos.value , isLoadingMore = false)
        }

        is UiState.NoNetwork -> TODO()
    }
}

@Composable
fun DisplayRepoList(
    listState: LazyListState,
    repos: List<SearchListItem>,
    isLoadingMore:Boolean
) {
    val chunkedRepos = repos.chunked(2)

    Column {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(5.dp)
                .weight(1f)
        ) {
            items(chunkedRepos.size) { index ->
                val itemPair = chunkedRepos[index]
                RepoRowItem(itemPair)
            }
            //Footer
            if(isLoadingMore){
                item {
                    LoadingFooter()
                }
            }
        }
    }
}

@Composable
fun RepoRowItem(itemPair: List<SearchListItem>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(modifier = Modifier.weight(1f)) {
            ListItem(itemPair[0])
        }
        Spacer(modifier = Modifier.width(10.dp))

        if (itemPair.size > 1) {
            Box(modifier = Modifier.weight(1f)) {
                ListItem(itemPair[1])
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ListItem(searchListItem: SearchListItem) {
    searchListItem.apply {
        Row {
            Card(
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(10.dp),
                border = BorderStroke(1.dp, colorResource(id = R.color.teal_300))
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(1f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(top = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GlideImage(
                            model = owner.avatarUrl,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(4.dp)
                                .clip(RoundedCornerShape(160.dp))
                                .size(50.dp)
                        )
                        Text(text = owner.login, modifier = Modifier.padding(10.dp), maxLines = 1)
                    }

                    HorizontalDivider()

                    Column(
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                    ) {
                        name?.let {
                            Text(
                                text = it,
                                fontSize = 16.sp,
                                maxLines = 1,
                                minLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        } ?: run {
                            Text(text = "", minLines = 1)
                        }
                        description?.let {
                            Text(
                                text = it,
                                fontSize = 13.sp,
                                maxLines = 2,
                                minLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        } ?: run {
                            Text(text = "", minLines = 2)
                        }
                        language?.let {
                            Card(
                                border = BorderStroke(1.dp, colorResource(id = R.color.teal_600))
                            ) {
                                Column(
                                    modifier = Modifier.padding(
                                        start = 10.dp,
                                        end = 10.dp,
                                        top = 3.dp,
                                        bottom = 3.dp
                                    ),
                                ) {
                                    Text(text = it, fontSize = 11.sp)
                                }
                            }
                        } ?: run {
                            Text(text = "")
                        }
                        Text(
                            text = updatedAt, fontSize = 11.sp, textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun test() {
    ListItem(
        SearchListItem(
            name = "123",
            description = "456",
            language = "kotlin",
            stargazersCount = 9999,
            updatedAt = "202505151139",
            owner = Owner(login = "Shawn", avatarUrl = "55")
        )
    )
}
