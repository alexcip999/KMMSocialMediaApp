package com.example.kmmsocialmediaapp.account.data.repository

import com.example.kmmsocialmediaapp.account.data.AccountApiService
import com.example.kmmsocialmediaapp.account.data.model.toDomainProfile
import com.example.kmmsocialmediaapp.account.data.model.toUserSettings
import com.example.kmmsocialmediaapp.account.domain.model.Profile
import com.example.kmmsocialmediaapp.account.domain.repository.ProfileRepository
import com.example.kmmsocialmediaapp.common.data.local.UserPreferences
import com.example.kmmsocialmediaapp.common.util.DispatcherProvider
import com.example.kmmsocialmediaapp.common.util.Result
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class ProfileRepositoryImpl(
    private val accountApiService: AccountApiService,
    private val preferences: UserPreferences,
    private val dispatcher: DispatcherProvider
) : ProfileRepository {

    override fun getProfile(profileId: Long): Flow<Result<Profile>> {
        return flow {
            val userData = preferences.getUserData()

            if(profileId == userData.id){
                emit(Result.Success(userData.toDomainProfile()))
            }

            val apiResponse = accountApiService.getProfile(
                token = userData.token,
                profileId = profileId,
                currentUserId = userData.id
            )

            when(apiResponse.code) {
                HttpStatusCode.OK -> {
                    val profile = apiResponse.data.profile!!.toProfile()

                    if (profileId == userData.id){
                        preferences.setUserData(profile.toUserSettings(userData.token))
                    }
                    emit(Result.Success(profile))
                }
                else -> {
                    emit(Result.Error(message = "Error: ${apiResponse.data.message}"))
                }
            }
        }.catch { exceprion ->
            emit(Result.Error(message = "Error: ${exceprion.message}"))
        }.flowOn(dispatcher.io)
    }

}