package com.example.joe.lastfmchallenge.data.models.album.details

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track(
    @SerializedName("name")
    private val name: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("duration")
    val duration: String
): Parcelable