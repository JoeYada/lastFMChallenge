package com.example.joe.lastfmchallenge.data.models.album.details

import com.example.joe.lastfmchallenge.data.models.album.Album
import com.google.gson.annotations.SerializedName

data class AlbumDetailsResponse(@SerializedName("album")
                           val album: Album
)