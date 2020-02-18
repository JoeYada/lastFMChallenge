package com.example.joe.lastfmchallenge.data.models.album.details

import com.google.gson.annotations.SerializedName

data class Wiki(
    @SerializedName("published") val published: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("content") val content: String
)