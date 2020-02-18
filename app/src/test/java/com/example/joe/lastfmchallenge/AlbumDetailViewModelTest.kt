package com.example.joe.lastfmchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.joe.lastfmchallenge.common.ApiProgress
import com.example.joe.lastfmchallenge.data.models.album.Album
import com.example.joe.lastfmchallenge.data.models.album.details.AlbumDetailsResponse
import com.example.joe.lastfmchallenge.data.models.album.details.Tracks
import com.example.joe.lastfmchallenge.data.repositories.ApiRepository
import com.example.joe.lastfmchallenge.ui.detail.AlbumDetailViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumDetailViewModelTest {

    @JvmField
    @Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ApiRepository


    lateinit var viewModel: AlbumDetailViewModel


    @Before
    fun setup() {
        viewModel = AlbumDetailViewModel(repository)
    }


    @Test
    fun apiReturnsSuccess_getAlbumShouldSetData() {

        val album = Album("", "", "", "", listOf(), "", "", Tracks(listOf()))
        Mockito.`when`(repository.getAlbumDetails("a", "a")).thenReturn(
            Single.just(
                AlbumDetailsResponse(album)
            )
        )

        viewModel.getAlbumDetails("a", "a")

        Assert.assertEquals(ApiProgress.SUCCESS, viewModel.progressLiveData.value)
        Assert.assertEquals(album, viewModel.albumDetails.value)
        Assert.assertEquals(null, viewModel.errorLiveData.value)

    }


    @Test
    fun apiReturnsException_getAlbumShouldSetError() {


        Mockito.`when`(repository.getAlbumDetails("a", "a")).thenReturn(
            Single.error(RuntimeException("Demo Error"))
        )

        viewModel.getAlbumDetails("a", "a")

        Assert.assertEquals(ApiProgress.FAILURE, viewModel.progressLiveData.value)
        Assert.assertEquals(null, viewModel.albumDetails.value)
        Assert.assertEquals("Demo Error", viewModel.errorLiveData.value)

    }


}