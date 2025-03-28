package com.example.kmmsocialmediaapp.account.domain.usecase

import com.example.kmmsocialmediaapp.account.domain.model.Profile
import com.example.kmmsocialmediaapp.account.domain.repository.ProfileRepository
import com.example.kmmsocialmediaapp.common.util.Result
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetProfileUseCase : KoinComponent {
    private val repository: ProfileRepository by inject()

    operator fun invoke(profileId: Long): Flow<Result<Profile>>{
        return repository.getProfile(profileId = profileId)
    }
}