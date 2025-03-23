package com.example.kmmsocialmediaapp.follows.domain.usecase

import com.example.kmmsocialmediaapp.common.domain.model.FollowsUser
import com.example.kmmsocialmediaapp.common.util.Result
import com.example.kmmsocialmediaapp.follows.domain.FollowsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetFollowableUsersUseCase : KoinComponent {
    private val repository by inject<FollowsRepository>()

    suspend operator fun invoke(): Result<List<FollowsUser>> {
        return repository.getFollowableUsers()
    }
}