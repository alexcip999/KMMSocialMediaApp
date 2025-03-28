package com.example.kmmsocialmediaapp.follows.data

import com.example.kmmsocialmediaapp.common.data.local.UserPreferences
import com.example.kmmsocialmediaapp.common.data.model.FollowsParams
import com.example.kmmsocialmediaapp.common.data.remote.FollowsApiService
import com.example.kmmsocialmediaapp.common.domain.model.FollowsUser
import com.example.kmmsocialmediaapp.common.util.Constants
import com.example.kmmsocialmediaapp.common.util.DispatcherProvider
import com.example.kmmsocialmediaapp.common.util.Result
import com.example.kmmsocialmediaapp.follows.domain.FollowsRepository
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.withContext

internal class FollowsRepositoryImpl (
    private val followsApiService: FollowsApiService,
    private val userPreferences: UserPreferences,
    private val dispatcher: DispatcherProvider
) : FollowsRepository{

    override suspend fun getFollowableUsers(): Result<List<FollowsUser>> {
        return withContext(dispatcher.io){
            try {
                val userData = userPreferences.getUserData()
                val apiResponse = followsApiService.getFollowableUsers(userData.token, userData.id)

                when (apiResponse.code){
                    HttpStatusCode.OK -> {
                        Result.Success(data = apiResponse.data.follows.map { it.toDomainFollowUser() })
                    }

                    HttpStatusCode.BadRequest -> {
                        Result.Error(message = "${apiResponse.data.message}")
                    }

                    HttpStatusCode.Forbidden -> {
                        Result.Success(data = emptyList())
                    }

                    else -> {
                        Result.Error(message = "${apiResponse.data.message}")
                    }
                }
            }catch (ioException: IOException){
                Result.Error(message = Constants.NO_INTERNET_ERROR)
            } catch (anyError: Throwable){
                Result.Error(
                    message = "${anyError.message}"
                )
            }
        }
    }

    override suspend fun followOrUnfollow(
        followedUserId: Long,
        shouldFollow: Boolean,
    ): Result<Boolean> {
        return withContext(dispatcher.io){
            try {
                val userData = userPreferences.getUserData()
                val followParams = FollowsParams(userData.id, followedUserId)
                val apiResponse = if (shouldFollow){
                    followsApiService.followUser(userData.token, followParams)
                } else {
                    followsApiService.followUser(userData.token, followParams)
                }

                if (apiResponse.code == HttpStatusCode.OK){
                    Result.Success(data = apiResponse.data.success)
                } else {
                    Result.Error(data = false, message = "${apiResponse.data.message}")
                }
            } catch (ioException: IOException){
                Result.Error(message = Constants.NO_INTERNET_ERROR)
            } catch (anyError: Throwable){
                Result.Error(
                    message = "${anyError.message}"
                )
            }
        }

    }

}