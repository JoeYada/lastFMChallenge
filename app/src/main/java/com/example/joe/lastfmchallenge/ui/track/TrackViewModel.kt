package com.example.joe.lastfmchallenge.ui.track

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.joe.lastfmchallenge.common.ApiProgress
import com.example.joe.lastfmchallenge.data.models.tracks.Track
import com.example.joe.lastfmchallenge.data.repositories.ApiRepository
import io.reactivex.disposables.CompositeDisposable

class TrackViewModel constructor(private val repository: ApiRepository):ViewModel() {

    val trackListLiveData = MutableLiveData<List<Track>>()
    val errorLiveData = MutableLiveData<String>()
    val progressLiveData = MutableLiveData<ApiProgress>()
    private val disposable= CompositeDisposable()
    fun getTrack(name:String){
        disposable.add(
            repository.getTrack(name)
                .subscribe({
                    if (it.results.trackmatches.track.isEmpty()){
                        errorLiveData.value = "$name Track not Found"
                        progressLiveData.value = ApiProgress.FAILURE
                    }else{
                        trackListLiveData.value = it.results.trackmatches.track
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