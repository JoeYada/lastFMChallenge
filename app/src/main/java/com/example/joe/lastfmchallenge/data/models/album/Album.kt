package com.example.joe.lastfmchallenge.data.models.album

import android.os.Parcelable
import com.example.joe.lastfmchallenge.data.models.Image
import com.example.joe.lastfmchallenge.data.models.album.details.Tracks
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    @SerializedName("name") val name: String,
    @SerializedName("artist") val artist: String,
    @SerializedName("mbid") val mbid: String,
    @SerializedName("url") val url: String,
    @SerializedName("image") val image: List<Image>,
    @SerializedName("listeners") val listeners: String,
    @SerializedName("playcount") val playcount: String,
    @SerializedName("tracks") val tracks: Tracks
) : Parcelable