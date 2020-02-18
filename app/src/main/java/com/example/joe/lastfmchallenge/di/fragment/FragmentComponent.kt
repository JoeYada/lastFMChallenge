package com.example.joe.lastfmchallenge.di.fragment

import com.example.joe.lastfmchallenge.ui.album.AlbumFragment
import com.example.joe.lastfmchallenge.ui.artist.ArtistFragment
import com.example.joe.lastfmchallenge.ui.detail.AlbumDetailFragment
import com.example.joe.lastfmchallenge.ui.track.TrackFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun injectAlbumFragment(albumFragment: AlbumFragment)
    fun injectArtistFragment(artistFragment: ArtistFragment)
    fun injectTrackFragment(trackFragment: TrackFragment)
    fun injectAlbumDetailFragment(albumDetailFragment: AlbumDetailFragment)

}