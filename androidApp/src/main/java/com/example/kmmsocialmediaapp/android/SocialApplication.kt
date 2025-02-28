package com.example.kmmsocialmediaapp.android

import android.app.Application
import com.example.kmmsocialmediaapp.android.di.appModule
import org.koin.core.context.startKoin

class SocialApplication:  Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}