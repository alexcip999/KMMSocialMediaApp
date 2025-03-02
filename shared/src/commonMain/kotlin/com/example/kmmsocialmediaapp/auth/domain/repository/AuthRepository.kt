package com.example.kmmsocialmediaapp.auth.domain.repository

import com.example.kmmsocialmediaapp.auth.domain.model.AuthResultData
import com.example.kmmsocialmediaapp.common.util.Result

internal interface AuthRepository {

    suspend fun signUp(
        name: String,
        email: String,
        password: String
    ) : Result<AuthResultData>

    suspend fun signIn(
        email: String,
        password: String
    ) : Result<AuthResultData>

}