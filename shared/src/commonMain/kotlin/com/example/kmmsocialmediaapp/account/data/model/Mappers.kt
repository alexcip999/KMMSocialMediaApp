package com.example.kmmsocialmediaapp.account.data.model

import com.example.kmmsocialmediaapp.account.domain.model.Profile
import com.example.kmmsocialmediaapp.common.data.local.UserSettings

fun UserSettings.toDomainProfile(): Profile {
    return Profile(
        id = id,
        name = name,
        bio = bio,
        imageUrl = avatar,
        followersCount = followersCount,
        followingCount = followingCount,
        isFollowing = false,
        isOwnProfile = true
    )
}

fun Profile.toUserSettings(token: String): UserSettings {
    return UserSettings(
        id = id,
        name = name,
        bio = bio,
        avatar = imageUrl,
        followersCount = followersCount,
        followingCount = followingCount,
        token = token
    )
}