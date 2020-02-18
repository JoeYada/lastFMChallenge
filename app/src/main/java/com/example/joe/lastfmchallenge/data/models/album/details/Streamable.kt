package com.example.joe.lastfmchallenge.data.models.album.details

import com.google.gson.annotations.SerializedName

data class Streamable(
    @SerializedName("#text")
    val text: String,
    @SerializedName("fulltrack")
    val fulltrack: String
)