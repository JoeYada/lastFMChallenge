package com.example.joe.lastfmchallenge.data.repositories

import com.example.joe.lastfmchallenge.data.models.album.AlbumResponse
import com.example.joe.lastfmchallenge.data.models.album.details.AlbumDetailsResponse
import com.example.joe.lastfmchallenge.data.models.artists.ArtistResponse
import com.example.joe.lastfmchallenge.data.models.tracks.TrackResponse
import io.reactivex.Single

interface ApiRepository {
    fun getAlbum(name: String): Single<AlbumResponse>
    fun getArtist(name: String): Single<ArtistResponse>
    fun getTrack(name: String):Single<TrackResponse>
    fun getAlbumDetails(artist:String, album:String): Single<AlbumDetailsResponse>
}