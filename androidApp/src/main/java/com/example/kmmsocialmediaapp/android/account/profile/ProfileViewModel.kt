package com.example.kmmsocialmediaapp.android.account.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmsocialmediaapp.android.common.dummy_data.SamplePost
import com.example.kmmsocialmediaapp.android.common.dummy_data.Profile
import com.example.kmmsocialmediaapp.android.common.dummy_data.samplePosts
import com.example.kmmsocialmediaapp.android.common.dummy_data.sampleProfiles
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel(){

    var userInfoUiState by mutableStateOf(UserInfoUiState())
        private set

    var profilePostsUiState by mutableStateOf(ProfilePostsUiState())
        private set

    fun fetchProfile(userId: Int){
        viewModelScope.launch {
            delay(1000)

            userInfoUiState = userInfoUiState.copy(
                isLoading = false,
                profile = sampleProfiles.find{ it.id == userId}
            )

            profilePostsUiState = profilePostsUiState.copy(
                isLoading = false,
                posts = samplePosts
            )

        }
    }
}

data class UserInfoUiState(
    val isLoading: Boolean = false,
    val profile: Profile? = null,
    val errorMessage: String? = null,
)

data class ProfilePostsUiState(
    val isLoading: Boolean = false,
    val posts: List<SamplePost> = listOf(),
    var errorMessage: String? = null

)