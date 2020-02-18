package com.example.joe.lastfmchallenge.data.models.tracks

import com.example.joe.lastfmchallenge.data.models.Image
import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("name") val name: String,
    @SerializedName("artist") val artist: String,
    @SerializedName("streamable") val streamable: String,
    @SerializedName("listeners") val listeners: String,
    @SerializedName("image") val image: List<Image>
)