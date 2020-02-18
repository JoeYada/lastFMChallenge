package com.example.joe.lastfmchallenge.di.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.joe.lastfmchallenge.data.repositories.ApiRepository
import com.example.joe.lastfmchallenge.ui.detail.AlbumDetailViewModel
import javax.inject.Inject

class AlbumDetailViewModelFactory @Inject constructor(val repository: ApiRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AlbumDetailViewModel::class.java)) AlbumDetailViewModel(repository) as T
        else throw IllegalArgumentException("Album Detail ViewModel Not Found")
    }
}