package com.example.kmmsocialmediaapp.android.home

import androidx.compose.runtime.Composable
import com.example.kmmsocialmediaapp.android.destinations.PostDetailDestination
import com.example.kmmsocialmediaapp.android.destinations.ProfileDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination(start = true)
fun Home(
    navigator: DestinationsNavigator
){
    val viewModel: HomeScreenViewModel = koinViewModel()

    HomeScreen(
        onBoardingUiState = viewModel.onBoardingUiState,
        postsUiState = viewModel.postsUiState,
        onPostClick = {
            navigator.navigate(PostDetailDestination(it.id))
        },
        onProfileClick = { navigator.navigate(ProfileDestination(it))},
        onLikeClick = { /*TODO*/ },
        onCommentClick = { /*TODO*/ },
        onFollowBottonClick = { _, _ -> },
        onBoardingFinish = { /*TODO*/ },
        fetchData = {
            viewModel.fetchData()
        }
    )
}