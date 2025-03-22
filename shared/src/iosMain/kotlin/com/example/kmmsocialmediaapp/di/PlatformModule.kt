package com.example.kmmsocialmediaapp.di

import com.example.kmmsocialmediaapp.common.data.IOSUserPreferences
import com.example.kmmsocialmediaapp.common.data.createDataStore
import com.example.kmmsocialmediaapp.common.data.local.UserPreferences
import org.koin.dsl.module

actual val platformModule = module {
    single<UserPreferences> { IOSUserPreferences(get()) }

    single {
        createDataStore()
    }
}