package com.example.joe.lastfmchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.joe.lastfmchallenge.common.ApiProgress
import com.example.joe.lastfmchallenge.data.models.artists.Artist
import com.example.joe.lastfmchallenge.data.models.artists.ArtistResponse
import com.example.joe.lastfmchallenge.data.models.artists.ArtistResults
import com.example.joe.lastfmchallenge.data.models.artists.Artistmatches
import com.example.joe.lastfmchallenge.data.repositories.ApiRepository
import com.example.joe.lastfmchallenge.ui.artist.ArtistViewModel
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
class ArtistViewModelTest {
    @JvmField
    @Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ApiRepository

    lateinit var viewModel: ArtistViewModel

    @Before
    fun setup() {
        viewModel = ArtistViewModel(repository)
    }

    @Test
    fun apiReturnsEmptyList_getArtistShouldSetError() {
        `when`(repository.getArtist("a")).thenReturn(
            Single.just(ArtistResponse(ArtistResults(Artistmatches(listOf()))))
        )

        viewModel.getArtist("a")
        Assert.assertEquals(ApiProgress.FAILURE, viewModel.progressLiveData.value)
        Assert.assertEquals(null, viewModel.artistListLiveData.value)
        Assert.assertEquals("No Result found for artist: a", viewModel.errorLiveData.value)
    }

    @Test
    fun apiReturnsNonEmptyList_getArtistShouldSetData() {
        val artists = listOf(Artist("j","5", "","no","", listOf()))
        `when`(repository.getArtist("j")).thenReturn(
            Single.just(ArtistResponse(ArtistResults(Artistmatches(artists))))
        )
        viewModel.getArtist("j")
        Assert.assertEquals(ApiProgress.SUCCESS, viewModel.progressLiveData.value)
        Assert.assertEquals(artists, viewModel.artistListLiveData.value)
        Assert.assertEquals(null, viewModel.errorLiveData.value)
    }

    @Test
    fun apiReturnsException_getArtistShouldSetError(){
        `when`(repository.getArtist("j")).thenReturn(
            Single.error(RuntimeException("Demo Artist Error"))
        )

        viewModel.getArtist("j")
        Assert.assertEquals(ApiProgress.FAILURE, viewModel.progressLiveData.value)
        Assert.assertEquals(null, viewModel.artistListLiveData.value)
        Assert.assertEquals("Demo Artist Error", viewModel.errorLiveData.value)
    }
}