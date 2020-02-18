package com.example.joe.lastfmchallenge.di.fragment

import com.example.joe.lastfmchallenge.data.repositories.ApiRepository
import com.example.joe.lastfmchallenge.di.factories.AlbumDetailViewModelFactory
import com.example.joe.lastfmchallenge.di.factories.AlbumViewModelFactory
import com.example.joe.lastfmchallenge.di.factories.ArtistViewModelFatory
import com.example.joe.lastfmchallenge.di.factories.TrackViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {
    @Provides
    @FragmentScope
    fun provideAlbumListFactory(repository: ApiRepository) = AlbumViewModelFactory(repository)

    @Provides
    @FragmentScope
    fun provideArtistListFactory(repository: ApiRepository) = ArtistViewModelFatory(repository)

    @Provides
    @FragmentScope
    fun provideTrackListFactory(repository: ApiRepository) = TrackViewModelFactory(repository)

    @Provides
    @FragmentScope
    fun provideAlbumDetailsFactory(repository: ApiRepository) = AlbumDetailViewModelFactory(repository)

}