package com.example.kmmsocialmediaapp.android.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmsocialmediaapp.android.common.dummy_data.Post
import com.example.kmmsocialmediaapp.android.common.dummy_data.samplePosts
import com.example.kmmsocialmediaapp.android.common.dummy_data.sampleUsers
import com.example.kmmsocialmediaapp.android.home.onboarding.OnBoardingUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    var postsUiState by mutableStateOf(PostsUiState())
        private set

    var onBoardingUiState by mutableStateOf(OnBoardingUiState())
        private set

    init {
        fetchData()
    }

    fun fetchData(){
        onBoardingUiState = onBoardingUiState.copy(isLoading = true)
        postsUiState = postsUiState.copy(
            isLoading = true
        )

        viewModelScope.launch {
            delay(1000)

            onBoardingUiState = onBoardingUiState.copy(
                isLoading = false,
                users = sampleUsers,
                shouldShowOnBoarding = true
            )

            postsUiState = postsUiState.copy(
                isLoading = false,
                posts = samplePosts
            )
        }
    }
}


data class PostsUiState(
    val isLoading: Boolean = false,
    val posts: List<Post> = listOf(),
    val errorMessage: String? = null
)