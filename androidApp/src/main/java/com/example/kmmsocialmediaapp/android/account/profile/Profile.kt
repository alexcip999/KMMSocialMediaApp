package com.example.kmmsocialmediaapp.android.account.profile

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun Profile(
    userId: Int,
    navigator: DestinationsNavigator
){

    val viewModel: ProfileViewModel = koinViewModel()

    ProfileScreen(
        userInfoUiState = viewModel.userInfoUiState,
        profilePostsUiState = viewModel.profilePostsUiState,
        onButtonClick = {/*TODO*/},
        onFollowersClick = {/*TODO*/},
        onFollowingClick = {/*TODO*/},
        onPostClick = {},
        onLikeClick = {},
        onCommentClick = {},
        fetchData = {viewModel.fetchProfile(userId)}
    )
}