package com.example.kmmsocialmediaapp.android.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmsocialmediaapp.android.common.dummy_data.SamplePost
import com.example.kmmsocialmediaapp.android.common.dummy_data.samplePosts
import com.example.kmmsocialmediaapp.common.domain.model.FollowsUser
import com.example.kmmsocialmediaapp.common.util.Result
import com.example.kmmsocialmediaapp.follows.domain.usecase.FollowOrUnfollowUseCase
import com.example.kmmsocialmediaapp.follows.domain.usecase.GetFollowableUsersUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getFollowableUsersUseCase: GetFollowableUsersUseCase,
    private val followOrUnfollowUseCase : FollowOrUnfollowUseCase,
) : ViewModel() {

    var postsFeedUiState by mutableStateOf(PostsFeedUiState())
        private set

    var onBoardingUiState by mutableStateOf(OnBoardingUiState())
        private set

    var homeRefreshState by mutableStateOf(HomeRefreshState())
        private set

    init {
        fetchData()
    }

    fun fetchData(){
        homeRefreshState = homeRefreshState.copy(isRefreshing = true)

        viewModelScope.launch {
            delay(1000)

            val users = getFollowableUsersUseCase()
            handleOnBoardingResult(users)
            postsFeedUiState = postsFeedUiState.copy(
                isLoading = false,
                posts = samplePosts
            )
            homeRefreshState = homeRefreshState.copy(isRefreshing = false)
        }
    }

    private fun handleOnBoardingResult(result: Result<List<FollowsUser>>){
        when(result) {
            is Result.Error -> Unit

            is Result.Success -> {
                result.data?.let { followsUsers ->
                    onBoardingUiState = onBoardingUiState.copy(
                        shouldShowOnBoarding = followsUsers.isNotEmpty(),
                        followableUsers = followsUsers
                    )
                }
            }
        }
    }

    private fun followUser(followsUser: FollowsUser) {
        viewModelScope.launch {
            Log.d("Tag1", "A intrat")

            val result = followOrUnfollowUseCase(
                followedUserId = followsUser.id,
                shouldFollow = !followsUser.isFollowing
            )

            onBoardingUiState = onBoardingUiState.copy(
                followableUsers = onBoardingUiState.followableUsers.map {
                    if (it.id == followsUser.id) {
                        it.copy(isFollowing = !followsUser.isFollowing)
                    } else it
                }
            )

            when (result) {
                is Result.Error -> {
                    Log.d("Tag1", result.message.toString())
                    onBoardingUiState = onBoardingUiState.copy(
                        followableUsers = onBoardingUiState.followableUsers.map {
                            if (it.id == followsUser.id) {
                                it.copy(isFollowing = followsUser.isFollowing)
                            } else it
                        }
                    )
                }

                is Result.Success -> Unit
            }
        }
    }

    private fun dimissOnboarding(){
        val hasFollowing = onBoardingUiState.followableUsers.any { it.isFollowing }
        if (!hasFollowing){
            Log.d("Tag3", "A intrat aici")
            // TODO tell the user they nedd to follow at least one person
        } else {
            Log.d("Tag2", "A intrat aici")
            onBoardingUiState =
                onBoardingUiState.copy(shouldShowOnBoarding = false, followableUsers = emptyList())
            fetchData()
        }
    }

    fun onUiAction(uiAction: HomeUiAction){
        when (uiAction) {
            is HomeUiAction.FollowUserAction -> followUser(uiAction.user)
            HomeUiAction.LoadMorePostsAction -> Unit
            is HomeUiAction.PostLikeAction -> Unit
            HomeUiAction.RefreshAction -> fetchData()
            HomeUiAction.RemoveOnboardingAction -> dimissOnboarding()
        }
    }
}

data class HomeRefreshState(
    val isRefreshing: Boolean = false,
    val refreshErrorMessage: String? = null
)

data class OnBoardingUiState(
    val shouldShowOnBoarding: Boolean = false,
    val followableUsers: List<FollowsUser> = listOf()
)

data class PostsFeedUiState(
    val isLoading: Boolean = false,
    val posts: List<SamplePost> = listOf(),
    val errorMessage: String? = null
)

sealed interface HomeUiAction {

    data class FollowUserAction(val user: FollowsUser) : HomeUiAction

    data class PostLikeAction(val post: com.example.kmmsocialmediaapp.common.domain.model.Post) :
        HomeUiAction

    data object RemoveOnboardingAction: HomeUiAction

    data object RefreshAction: HomeUiAction

    data object LoadMorePostsAction: HomeUiAction
}