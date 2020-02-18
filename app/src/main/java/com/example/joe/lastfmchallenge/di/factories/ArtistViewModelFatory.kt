package com.example.joe.lastfmchallenge.di.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.joe.lastfmchallenge.data.repositories.ApiRepository
import com.example.joe.lastfmchallenge.ui.artist.ArtistViewModel
import javax.inject.Inject

class ArtistViewModelFatory @Inject constructor(val repository: ApiRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) ArtistViewModel(repository) as T
        else throw IllegalArgumentException("Artist ViewModel Not Found")
    }
}