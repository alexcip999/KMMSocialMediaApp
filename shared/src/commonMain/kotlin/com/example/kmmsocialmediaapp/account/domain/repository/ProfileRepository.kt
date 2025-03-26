package com.example.kmmsocialmediaapp.account.domain.repository

import com.example.kmmsocialmediaapp.account.domain.model.Profile
import com.example.kmmsocialmediaapp.common.util.Result
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getProfile(profileId: Long): Flow<Result<Profile>>
}