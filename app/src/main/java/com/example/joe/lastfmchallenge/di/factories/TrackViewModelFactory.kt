package com.example.joe.lastfmchallenge.di.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.joe.lastfmchallenge.data.repositories.ApiRepository
import com.example.joe.lastfmchallenge.ui.track.TrackViewModel
import javax.inject.Inject

class TrackViewModelFactory @Inject constructor(private val repository: ApiRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TrackViewModel::class.java)) TrackViewModel(repository) as T
        else throw IllegalArgumentException("Track ViewModel Not Found")
    }
}