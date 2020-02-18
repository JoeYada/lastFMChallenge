package com.example.joe.lastfmchallenge.di.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.joe.lastfmchallenge.data.repositories.ApiRepository
import com.example.joe.lastfmchallenge.ui.album.AlbumViewModel
import javax.inject.Inject

class AlbumViewModelFactory @Inject constructor(private val repository: ApiRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) AlbumViewModel(repository) as T
        else throw IllegalArgumentException("Album ViewModel Not Found")
    }
}