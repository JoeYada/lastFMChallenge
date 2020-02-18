package com.example.joe.lastfmchallenge.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.joe.lastfmchallenge.common.ApiProgress
import com.example.joe.lastfmchallenge.data.models.album.Album
import com.example.joe.lastfmchallenge.data.repositories.ApiRepository
import io.reactivex.disposables.CompositeDisposable

class AlbumDetailViewModel constructor(private val repository: ApiRepository): ViewModel(){

    val albumDetails = MutableLiveData<Album>()
    val errorLiveData = MutableLiveData<String>()
    val progressLiveData = MutableLiveData<ApiProgress>()
    private val disposable = CompositeDisposable()
    fun getAlbumDetails(artist: String, album: String) {
        progressLiveData.value = ApiProgress.LOADING
        disposable.add(
            repository.getAlbumDetails(artist, album)
                .subscribe({
                    albumDetails.value = it.album
                    progressLiveData.value = ApiProgress.SUCCESS
                }, {
                    errorLiveData.value = it.localizedMessage
                    progressLiveData.value = ApiProgress.FAILURE
                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}