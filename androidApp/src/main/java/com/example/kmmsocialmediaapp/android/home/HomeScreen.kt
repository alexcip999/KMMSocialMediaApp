package com.example.kmmsocialmediaapp.android.home

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kmmsocialmediaapp.android.common.components.PostListItem
import com.example.kmmsocialmediaapp.android.common.fake_data.FollowsUser
import com.example.kmmsocialmediaapp.android.common.fake_data.Post
import com.example.kmmsocialmediaapp.android.common.fake_data.samplePosts
import com.example.kmmsocialmediaapp.android.common.fake_data.sampleUsers
import com.example.kmmsocialmediaapp.android.common.theming.SocialAppTheme
import com.example.kmmsocialmediaapp.android.common.theming.appColors
import com.example.kmmsocialmediaapp.android.home.onboarding.OnBoardingSection
import com.example.kmmsocialmediaapp.android.home.onboarding.OnBoardingUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onBoardingUiState: OnBoardingUiState,
    postsUiState: PostsUiState,
    onPostClick: (Post) -> Unit,
    onProfileClick: (Int) -> Unit,
    onCommentClick: () -> Unit,
    onLikeClick: () -> Unit,
    onFollowBottonClick: (Boolean, FollowsUser) -> Unit,
    onBoardingFinish: () -> Unit,
    fetchData: () -> Unit,
){
    val pullRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        state = pullRefreshState,
        isRefreshing = onBoardingUiState.isLoading && postsUiState.isLoading,
        onRefresh = { fetchData() },
        modifier = modifier
            .fillMaxSize()
    ){
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            if (onBoardingUiState.shouldShowOnBoarding){
                item(key = "onboardingsection"){
                    OnBoardingSection(
                        users = onBoardingUiState.users,
                        onUserClick = { onProfileClick(it.id) },
                        onFollowButtonClick = onFollowBottonClick
                    ){
                        onBoardingFinish()
                    }
                }
            }

            items(items = postsUiState.posts, key = {post -> post.id}){
                PostListItem(
                    post = it,
                    onPostClick = onPostClick,
                    onProfileClick = onProfileClick,
                    onLikeClick = onLikeClick,
                    onCommnetClick = onCommentClick

                )
            }
        }

        PullToRefreshDefaults.Indicator(
            state = pullRefreshState,
            isRefreshing = onBoardingUiState.isLoading && postsUiState.isLoading,
            modifier = modifier.align(Alignment.TopCenter)
        )
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
                onBoardingUiState = OnBoardingUiState(
                    users = sampleUsers,
                    shouldShowOnBoarding = true
                ),
                postsUiState = PostsUiState(
                    posts = samplePosts
                ),
                onFollowBottonClick = { _, _ -> },
                onPostClick = {},
                onProfileClick = {},
                onLikeClick = {},
                onCommentClick = {},
                fetchData = {},
                onBoardingFinish = {}
            )
        }
    }
}