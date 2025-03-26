package com.example.kmmsocialmediaapp.post.data

import com.example.kmmsocialmediaapp.common.data.local.UserPreferences
import com.example.kmmsocialmediaapp.common.data.local.UserSettings
import com.example.kmmsocialmediaapp.common.data.model.LikeParams
import com.example.kmmsocialmediaapp.common.data.model.PostsApiResponse
import com.example.kmmsocialmediaapp.common.data.remote.PostApiService
import com.example.kmmsocialmediaapp.common.domain.model.Post
import com.example.kmmsocialmediaapp.common.util.Constants
import com.example.kmmsocialmediaapp.common.util.DispatcherProvider
import com.example.kmmsocialmediaapp.common.util.Result
import com.example.kmmsocialmediaapp.post.domain.PostRepository
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.withContext

internal class PostRepositoryImpl (
    private val postApiService: PostApiService,
    private val userPreferences: UserPreferences,
    private val dispatcher: DispatcherProvider
): PostRepository {
    override suspend fun getFeedPosts(page: Int, pageSize: Int): Result<List<Post>> {
        return withContext(dispatcher.io) {
            try {
                val userData = userPreferences.getUserData()
                val apiResponse = postApiService.getFeedPosts(
                    userToken = userData.token,
                    currentUserId = userData.id,
                    page = page,
                    pageSize = pageSize
                )

                when (apiResponse.code) {
                    HttpStatusCode.OK -> {
                        Result.Success(data = apiResponse.data.posts.map { it.toDomainPost() })
                    }

                    HttpStatusCode.BadRequest -> {
                        Result.Error(message = "Error: ${apiResponse.data.message}")
                    }

                    else -> {
                        Result.Error(message = Constants.UNEXPECTED_ERROR)
                    }
                }
            } catch (ioException: IOException){
                Result.Error(message = Constants.NO_INTERNET_ERROR)
            } catch (exception: Throwable){
                Result.Error(
                    message = "${exception.cause}"
                )
            }
        }
    }

    override suspend fun likeOrUnlikePost(postId: Long, shouldLike: Boolean): Result<Boolean> {
        return withContext(dispatcher.io){
            try {
                val userData = userPreferences.getUserData()
                val likeParams = LikeParams(postId = postId, userId = userData.id)

                val apiResponse = if (shouldLike){
                    postApiService.likePost(userData.token, likeParams)
                } else {
                    postApiService.dislikepost(userData.token, likeParams)
                }

                if (apiResponse.code == HttpStatusCode.OK){
                    Result.Success(data = apiResponse.data.success)
                }else{
                    Result.Error(data = false, message = "${apiResponse.data.message}")
                }
            } catch (ioException: IOException){
                Result.Error(message = Constants.NO_INTERNET_ERROR)
            } catch (exception: Throwable){
                Result.Error(
                    message = "${exception.message}"
                )
            }
        }
    }

    override suspend fun getUserPosts(userId: Long, page: Int, pageSize: Int): Result<List<Post>> {
        return fetchPosts(
            apiCall = { currentUserData ->
                postApiService.getUserPosts(
                    token = currentUserData.token,
                    userId = userId,
                    currentUserId = currentUserData.id,
                    page = page,
                    pageSize = pageSize
                )
            }
        )
    }

    private suspend fun fetchPosts(
        apiCall: suspend (UserSettings) -> PostsApiResponse
    ) : Result<List<Post>> {
        return withContext(dispatcher.io){
            try {
                val currentUserData = userPreferences.getUserData()
                val apiResponse = apiCall(currentUserData)

                when (apiResponse.code) {
                    HttpStatusCode.OK -> {
                        Result.Success(data = apiResponse.data.posts.map { it.toDomainPost() })
                    }
                    else -> {
                        Result.Error(message = Constants.UNEXPECTED_ERROR)
                    }
                }
            } catch (ioException: IOException){
                Result.Error(message = Constants.NO_INTERNET_ERROR)
            } catch (exception: Throwable) {
                Result.Error(message = "${exception.cause}")
            }
        }
    }

}