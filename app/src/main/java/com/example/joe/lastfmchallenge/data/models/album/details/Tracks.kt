package com.example.joe.lastfmchallenge.data.models.album.details

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tracks(@SerializedName("track")
             val track:List<Track>):Parcelable {
}