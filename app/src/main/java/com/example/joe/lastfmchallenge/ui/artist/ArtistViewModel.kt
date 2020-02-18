package com.example.joe.lastfmchallenge.ui.artist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.joe.lastfmchallenge.common.ApiProgress
import com.example.joe.lastfmchallenge.data.models.artists.Artist
import com.example.joe.lastfmchallenge.data.repositories.ApiRepository
import io.reactivex.disposables.CompositeDisposable

class ArtistViewModel constructor(private val repository: ApiRepository): ViewModel() {
    val artistListLiveData = MutableLiveData<List<Artist>>()
    val errorLiveData = MutableLiveData<String>()
    val progressLiveData = MutableLiveData<ApiProgress>()
    private val disposable = CompositeDisposable()

    fun getArtist(name:String){
        progressLiveData.value = ApiProgress.LOADING
        disposable.add(
            repository.getArtist(name)
                .subscribe({
                    if (it.results.artistmatches.artist.isEmpty()){
                        errorLiveData.value = "No Result found for artist: $name"
                        progressLiveData.value = ApiProgress.FAILURE
                    }else{
                        artistListLiveData.value = it.results.artistmatches.artist
                        progressLiveData.value = ApiProgress.SUCCESS
                    }
                },{
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