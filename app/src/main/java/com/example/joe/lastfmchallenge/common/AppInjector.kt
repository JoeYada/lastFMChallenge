package com.example.joe.lastfmchallenge.common

import android.app.Application
import com.example.joe.lastfmchallenge.di.application.ApplicationComponent
import com.example.joe.lastfmchallenge.di.application.ApplicationModule
import com.example.joe.lastfmchallenge.di.application.DaggerApplicationComponent

class AppInjector: Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}