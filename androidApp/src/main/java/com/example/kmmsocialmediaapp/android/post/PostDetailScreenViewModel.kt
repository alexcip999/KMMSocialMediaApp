package com.example.kmmsocialmediaapp.android.post

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmsocialmediaapp.android.common.dummy_data.Comment
import com.example.kmmsocialmediaapp.android.common.dummy_data.SamplePost
import com.example.kmmsocialmediaapp.android.common.dummy_data.sampleComments
import com.example.kmmsocialmediaapp.android.common.dummy_data.samplePosts
import kotlinx.coroutines.launch

class PostDetailScreenViewModel: ViewModel() {

    var postUiState by mutableStateOf(PostUiState())
        private set

    var commentsUiState by mutableStateOf(CommentsUiState())
        private set

    fun fetchData(postId: String){
        viewModelScope.launch {
//            postUiState = postUiState.copy(
//                isLoading = true
//            )
//
//            commentsUiState = commentsUiState.copy(
//                isLoading = true
//            )
//
//            delay(500)

            postUiState = postUiState.copy(
                isLoading = false,
                post = samplePosts.find { it.id == postId }
            )

            commentsUiState = commentsUiState.copy(
                isLoading = false,
                comments = sampleComments
            )

        }
    }
}

data class PostUiState(
    val isLoading: Boolean = false,
    val post: SamplePost? = null,
    val errorMessage: String? = null
)

data class CommentsUiState(
    val isLoading: Boolean = false,
    val comments: List<Comment> = listOf(),
    val errorMessage: String? = null
)