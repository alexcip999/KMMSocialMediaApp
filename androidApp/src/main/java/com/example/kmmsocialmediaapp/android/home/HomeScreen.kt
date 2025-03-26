package com.example.kmmsocialmediaapp.android.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.kmmsocialmediaapp.android.R
import com.example.kmmsocialmediaapp.android.common.components.PostListItem
import com.example.kmmsocialmediaapp.android.common.theming.LargeSpacing
import com.example.kmmsocialmediaapp.android.common.theming.MediumSpacing
import com.example.kmmsocialmediaapp.android.common.theming.SocialAppTheme
import com.example.kmmsocialmediaapp.android.common.theming.appColors
import com.example.kmmsocialmediaapp.android.common.util.Constants
import com.example.kmmsocialmediaapp.android.home.onboarding.OnBoardingSection
import com.example.kmmsocialmediaapp.common.domain.model.Post

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onBoardingUiState: OnBoardingUiState,
    postsFeedUiState: PostsFeedUiState,
    homeRefreshState: HomeRefreshState,
    onUiAction: (HomeUiAction) -> Unit,
    onProfileNavigation: (userId: Long) -> Unit,
    onPostDetailNavigation: (Post) -> Unit
){
    val pullRefreshState = rememberPullToRefreshState()

    val listState = rememberLazyListState()
    val shouldFetchMorePosts by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo

            if (layoutInfo.totalItemsCount == 0){
                false
            } else {
                val lastVisibleItem = visibleItemsInfo.last()
                (lastVisibleItem.index + 1 == layoutInfo.totalItemsCount)
            }
        }
    }

    PullToRefreshBox(
        state = pullRefreshState,
        isRefreshing = homeRefreshState.isRefreshing,
        onRefresh = { onUiAction(HomeUiAction.RefreshAction) },
        modifier = modifier
            .fillMaxSize()
    ){
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            state = listState
        ) {
            if (onBoardingUiState.shouldShowOnBoarding){
                item(key = "onboardingsection"){
                    OnBoardingSection(
                        users = onBoardingUiState.followableUsers,
                        onUserClick = { onProfileNavigation(it.id) },
                        onFollowButtonClick = {_, user ->
                            onUiAction(
                                HomeUiAction.FollowUserAction(
                                    user
                                )
                            )
                        },
                        onBoardingFinish = { onUiAction(HomeUiAction.RemoveOnboardingAction) }
                    )

                    Text(
                        text = stringResource(id = R.string.trending_posts_title),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(bottom = LargeSpacing),
                        textAlign = TextAlign.Center
                    )
                }
            }

            items(items = postsFeedUiState.posts, key = { post -> post.postId}){ post ->
                PostListItem(
                    post = post,
                    onPostClick = { onPostDetailNavigation(it) },
                    onProfileClick = { onProfileNavigation(it) },
                    onLikeClick = { onUiAction(HomeUiAction.PostLikeAction(it))},
                    onCommnetClick = {onPostDetailNavigation(it)}

                )
            }

            if (postsFeedUiState.isLoading && postsFeedUiState.posts.isNotEmpty()) {
                item(key = Constants.LOADING_MORE_ITEM_KEY) {
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = MediumSpacing, horizontal = LargeSpacing),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                }
            }
        }

        PullToRefreshDefaults.Indicator(
            state = pullRefreshState,
            isRefreshing = homeRefreshState.isRefreshing,
            modifier = modifier.align(Alignment.TopCenter)
        )

        LaunchedEffect(key1 = shouldFetchMorePosts) {
            if (shouldFetchMorePosts && !postsFeedUiState.endReached){
                onUiAction(HomeUiAction.LoadMorePostsAction)
            }
        }
    }


}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview(){
    SocialAppTheme {
        Surface(
            color = MaterialTheme.appColors.background
        ){
            HomeScreen(
                onBoardingUiState = OnBoardingUiState(),
                postsFeedUiState = PostsFeedUiState(),
                homeRefreshState = HomeRefreshState(),
                onPostDetailNavigation = {},
                onProfileNavigation = {},
                onUiAction = {}
            )
        }
    }
}