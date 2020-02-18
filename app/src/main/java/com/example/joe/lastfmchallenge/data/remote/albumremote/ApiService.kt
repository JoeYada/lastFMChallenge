package com.example.joe.lastfmchallenge.data.remote.albumremote

import com.example.joe.lastfmchallenge.data.models.album.AlbumResponse
import com.example.joe.lastfmchallenge.data.models.album.details.AlbumDetailsResponse
import com.example.joe.lastfmchallenge.data.models.artists.ArtistResponse
import com.example.joe.lastfmchallenge.data.models.tracks.TrackResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(".")
    fun getAlbum(
        @Query("method") method:String,
        @Query("album") album:String,
        @Query("api_key") key: String,
        @Query("format") format:String): Single<AlbumResponse>

    @GET(".")
    fun getArtist(
        @Query("method") method:String,
        @Query("artist") artist:String,
        @Query("api_key") key: String,
        @Query("format") format:String): Single<ArtistResponse>

    @GET(".")
    fun getTrack(
        @Query("method") method:String,
        @Query("track") track:String,
        @Query("api_key") key: String,
        @Query("format") format:String): Single<TrackResponse>

    @GET(".")
    fun getAlbumDetails(
        @Query("method") method:String,
        @Query("artist") artist:String,
        @Query("album") album:String,
        @Query("api_key") key: String,
        @Query("format") format:String): Single<AlbumDetailsResponse>
}