package com.example.joe.lastfmchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.joe.lastfmchallenge.common.ApiProgress
import com.example.joe.lastfmchallenge.data.models.tracks.Track
import com.example.joe.lastfmchallenge.data.models.tracks.TrackResponse
import com.example.joe.lastfmchallenge.data.models.tracks.TrackResults
import com.example.joe.lastfmchallenge.data.models.tracks.Trackmatches
import com.example.joe.lastfmchallenge.data.repositories.ApiRepository
import com.example.joe.lastfmchallenge.ui.track.TrackViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrackViewModelTest {
    @JvmField
    @Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ApiRepository

    lateinit var viewModel: TrackViewModel

    @Before
    fun setup() {
        viewModel = TrackViewModel(repository)
    }

    @Test
    fun apiReturnsEmptyList_getTrackShouldSetError() {
        `when`(repository.getTrack("a")).thenReturn(
            Single.just(TrackResponse(TrackResults(Trackmatches(listOf()))))
        )

        viewModel.getTrack("a")
        Assert.assertEquals(ApiProgress.FAILURE, viewModel.progressLiveData.value)
        Assert.assertEquals(null, viewModel.trackListLiveData.value)
        Assert.assertEquals("a Track not Found", viewModel.errorLiveData.value)
    }

    @Test
    fun apiReturnsNonEmptyList_getTrackShouldSetData() {
        val tracks = listOf(Track("j","adele", "no","5", listOf()))
        `when`(repository.getTrack("j")).thenReturn(
            Single.just(TrackResponse(TrackResults(Trackmatches(tracks))))
        )
        viewModel.getTrack("j")
        Assert.assertEquals(ApiProgress.SUCCESS, viewModel.progressLiveData.value)
        Assert.assertEquals(tracks, viewModel.trackListLiveData.value)
        Assert.assertEquals(null, viewModel.errorLiveData.value)
    }

    @Test
    fun apiReturnsException_getTrackShouldSetError(){
        `when`(repository.getTrack("j")).thenReturn(
            Single.error(RuntimeException("Demo Track Error"))
        )

        viewModel.getTrack("j")
        Assert.assertEquals(ApiProgress.FAILURE, viewModel.progressLiveData.value)
        Assert.assertEquals(null, viewModel.trackListLiveData.value)
        Assert.assertEquals("Demo Track Error", viewModel.errorLiveData.value)
    }
}