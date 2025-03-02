package com.example.kmmsocialmediaapp.auth.data

import com.example.kmmsocialmediaapp.auth.domain.model.AuthResultData

internal fun AuthResponseData.toAuthResultData(): AuthResultData {
    return AuthResultData(id, name, bio, avatar, token, followersCount)
}