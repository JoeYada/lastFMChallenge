package com.example.joe.lastfmchallenge.di.application

import android.app.Application
import android.content.Context
import com.example.joe.lastfmchallenge.data.remote.albumremote.ApiService
import com.example.joe.lastfmchallenge.data.remote.albumremote.NetworkModule
import com.example.joe.lastfmchallenge.data.repositories.NetworkApiRepository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class ApplicationModule(private val application: Application) {
    @Provides
    @ApplicationScope
    fun providesContext(): Context = application

    @Provides
    @ApplicationScope
    fun providesRepo(apiService: ApiService) : NetworkApiRepository = NetworkApiRepository(apiService)
}