package com.example.kmmsocialmediaapp.android.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.kmmsocialmediaapp.android.MainActivityViewModel
import com.example.kmmsocialmediaapp.android.auth.login.LoginViewModel
import com.example.kmmsocialmediaapp.android.auth.signup.SignUpViewModel
import com.example.kmmsocialmediaapp.android.common.datastore.UserSettingsSerializer
import com.example.kmmsocialmediaapp.android.home.HomeScreenViewModel
import com.example.kmmsocialmediaapp.android.post.PostDetailScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SignUpViewModel(get(), get()) }
    viewModel { MainActivityViewModel(get()) }
    viewModel { HomeScreenViewModel() }
    viewModel { PostDetailScreenViewModel() }

    single {
        DataStoreFactory.create(
            serializer = UserSettingsSerializer,
            produceFile = {
                androidContext().dataStoreFile(
                    fileName = "app_user_settings"
                )
            }
        )
    }
}