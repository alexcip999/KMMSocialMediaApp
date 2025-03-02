package com.example.kmmsocialmediaapp.auth.domain.usecase

import com.example.kmmsocialmediaapp.auth.domain.model.AuthResultData
import com.example.kmmsocialmediaapp.auth.domain.repository.AuthRepository
import com.example.kmmsocialmediaapp.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignInUseCase: KoinComponent {
    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<AuthResultData> {
        if (email.isBlank() || "@" !in email){
            return Result.Error(
                message = "Invalid email"
            )
        }

        if (password.isBlank() || password.length < 4){
            return Result.Error(
                message = "Invalid password or too short!"
            )
        }

        return repository.signIn(email, password)
    }
}