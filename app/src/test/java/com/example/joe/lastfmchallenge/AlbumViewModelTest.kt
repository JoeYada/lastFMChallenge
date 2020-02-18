package com.example.joe.lastfmchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.joe.lastfmchallenge.common.ApiProgress
import com.example.joe.lastfmchallenge.data.models.album.Album
import com.example.joe.lastfmchallenge.data.models.album.AlbumResponse
import com.example.joe.lastfmchallenge.data.models.album.Albummatches
import com.example.joe.lastfmchallenge.data.models.album.Results
import com.example.joe.lastfmchallenge.data.models.album.details.Tracks
import com.example.joe.lastfmchallenge.data.repositories.ApiRepository
import com.example.joe.lastfmchallenge.ui.album.AlbumViewModel
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
class AlbumViewModelTest {

    @JvmField
    @Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ApiRepository


    lateinit var viewModel: AlbumViewModel


    @Before
    fun setup() {
        viewModel = AlbumViewModel(repository)
    }

    @Test
    fun apiReturnsEmptyList_getAlbumShouldSetError() {

        `when`(repository.getAlbum("a")).thenReturn(
            Single.just(
                AlbumResponse(
                    Results(
                        Albummatches(
                            listOf()
                        )
                    )
                )
            )
        )

        viewModel.getAlbum("a")

        Assert.assertEquals(ApiProgress.FAILURE, viewModel.progressLiveData.value)
        Assert.assertEquals(null, viewModel.albumListLiveData.value)
        Assert.assertEquals("No Result album found for a", viewModel.errorLiveData.value)

    }


    @Test
    fun apiReturnsNonEmptyList_getAlbumShouldSetData() {

        val albumList = listOf( Album("", "", "", "", listOf(), "", "", Tracks(listOf())))
        `when`(repository.getAlbum("a")).thenReturn(
            Single.just(
                AlbumResponse(
                    Results(
                        Albummatches(
                            albumList
                        )
                    )
                )
            )
        )

        viewModel.getAlbum("a")

        Assert.assertEquals(ApiProgress.SUCCESS, viewModel.progressLiveData.value)
        Assert.assertEquals(albumList, viewModel.albumListLiveData.value)
        Assert.assertEquals(null, viewModel.errorLiveData.value)

    }


    @Test
    fun apiReturnsException_getAlbumShouldSetError() {


        `when`(repository.getAlbum("a")).thenReturn(
            Single.error(RuntimeException("Demo Error"))
        )

        viewModel.getAlbum("a")

        Assert.assertEquals(ApiProgress.FAILURE, viewModel.progressLiveData.value)
        Assert.assertEquals(null, viewModel.albumListLiveData.value)
        Assert.assertEquals("Demo Error", viewModel.errorLiveData.value)

    }


}