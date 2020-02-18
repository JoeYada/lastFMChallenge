package com.example.joe.lastfmchallenge.di.application

import com.example.joe.lastfmchallenge.di.fragment.FragmentComponent
import com.example.joe.lastfmchallenge.di.fragment.FragmentModule
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun newActivityComponent(fragmentModule: FragmentModule) : FragmentComponent
}