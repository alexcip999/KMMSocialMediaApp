package com.example.kmmsocialmediaapp.account.data.model

import com.example.kmmsocialmediaapp.account.domain.model.Profile
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable

@Serializable
internal data class RemoteProfile(
    val id: Long,
    val name: String,
    val bio: String,
    val imageUrl: String?,
    val followersCount: Int,
    val followingCount: Int,
    val isFollowing: Boolean,
    val isOwnProfile: Boolean
){
    fun toProfile(): Profile {
        return Profile(
            id, name, bio, imageUrl, followersCount, followingCount, isFollowing, isOwnProfile
        )
    }
}

@Serializable
internal data class ProfileApiResponseData(
    val success: Boolean,
    val profile: RemoteProfile?,
    val message: String?
)

internal data class ProfileApiResponse(
    val code: HttpStatusCode,
    val data: ProfileApiResponseData
)