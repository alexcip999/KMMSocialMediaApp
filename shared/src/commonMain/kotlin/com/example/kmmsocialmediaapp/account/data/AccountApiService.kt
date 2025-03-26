package com.example.kmmsocialmediaapp.account.data

import com.example.kmmsocialmediaapp.account.data.model.ProfileApiResponse
import com.example.kmmsocialmediaapp.common.data.remote.KtorApi
import com.example.kmmsocialmediaapp.common.util.Constants
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class AccountApiService : KtorApi() {
    suspend fun getProfile(
        token: String,
        profileId: Long,
        currentUserId: Long
    ): ProfileApiResponse {
        val httpResponse = client.get {
            endPoint(path = "/profile/$profileId")
            parameter(key = Constants.CURRENT_USER_ID_PARAMETER, value = currentUserId)
            setToken(token = token)
        }

        return ProfileApiResponse(code = httpResponse.status, data = httpResponse.body())
    }
}