package com.example.kmmsocialmediaapp.di

import com.example.kmmsocialmediaapp.auth.data.AuthRepositoryImpl
import com.example.kmmsocialmediaapp.auth.data.AuthService
import com.example.kmmsocialmediaapp.auth.domain.repository.AuthRepository
import com.example.kmmsocialmediaapp.auth.domain.usecase.SignInUseCase
import com.example.kmmsocialmediaapp.auth.domain.usecase.SignUpUseCase
import com.example.kmmsocialmediaapp.common.util.provideDispatcher
import org.koin.dsl.module

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory { AuthService() }
    factory { SignUpUseCase() }
    factory { SignInUseCase() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

fun getSharedModules() = listOf( authModule, utilityModule)
