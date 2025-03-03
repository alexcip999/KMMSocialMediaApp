package com.example.kmmsocialmediaapp.android.di

import com.example.kmmsocialmediaapp.android.auth.login.LoginViewModel
import com.example.kmmsocialmediaapp.android.auth.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
}