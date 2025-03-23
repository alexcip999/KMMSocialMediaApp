package com.example.kmmsocialmediaapp.follows.domain

import com.example.kmmsocialmediaapp.common.domain.model.FollowsUser
import com.example.kmmsocialmediaapp.common.util.Result

interface FollowsRepository {
    suspend fun getFollowableUsers(): Result<List<FollowsUser>>

    suspend fun followOrUnfollow(followedUserId: Long, shouldFollow: Boolean): Result<Boolean>
}