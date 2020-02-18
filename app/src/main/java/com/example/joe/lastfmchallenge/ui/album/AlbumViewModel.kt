package com.example.joe.lastfmchallenge.ui.album

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.joe.lastfmchallenge.common.ApiProgress
import com.example.joe.lastfmchallenge.data.models.album.Album
import com.example.joe.lastfmchallenge.data.repositories.ApiRepository
import io.reactivex.disposables.CompositeDisposable

class AlbumViewModel constructor(private val repository: ApiRepository) : ViewModel() {

    val albumListLiveData = MutableLiveData<List<Album>>()
    val errorLiveData = MutableLiveData<String>()
    val progressLiveData = MutableLiveData<ApiProgress>()
    private val disposable = CompositeDisposable()
    fun getAlbum(name: String) {
        progressLiveData.value = ApiProgress.LOADING
        disposable.add(
            repository.getAlbum(name)
                .subscribe({
                    if (it.results.albummatches.album.isEmpty()) {
                        errorLiveData.value = "No Result album found for $name"
                        progressLiveData.value = ApiProgress.FAILURE
                    } else {
                        albumListLiveData.value = it.results.albummatches.album
                        progressLiveData.value = ApiProgress.SUCCESS
                    }
                }, {
                    errorLiveData.value = it.localizedMessage
                    progressLiveData.value = ApiProgress.FAILURE
                })
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}