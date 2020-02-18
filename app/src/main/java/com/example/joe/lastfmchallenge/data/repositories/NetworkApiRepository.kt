package com.example.joe.lastfmchallenge.data.repositories

import com.example.joe.lastfmchallenge.common.*
import com.example.joe.lastfmchallenge.data.models.album.AlbumResponse
import com.example.joe.lastfmchallenge.data.models.album.details.AlbumDetailsResponse
import com.example.joe.lastfmchallenge.data.models.artists.ArtistResponse
import com.example.joe.lastfmchallenge.data.models.tracks.TrackResponse
import com.example.joe.lastfmchallenge.data.remote.albumremote.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NetworkApiRepository(private val apiService: ApiService) : ApiRepository {
    override fun getAlbum(name: String): Single<AlbumResponse> {
        return apiService.getAlbum(ALBUM_SEARCH, name, API_KEY, "json")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getArtist(name: String): Single<ArtistResponse> {
        return apiService.getArtist(ARTIST_SEARCH, name, API_KEY, "json")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getTrack(name: String): Single<TrackResponse> {
        return apiService.getTrack(TRACK_SEARCH, name, API_KEY, "json")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAlbumDetails(artist: String, album: String): Single<AlbumDetailsResponse> {
        return apiService.getAlbumDetails(ALBUM_GET_INFO,artist,album, API_KEY,"json")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
