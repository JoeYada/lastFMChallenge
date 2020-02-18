package com.example.joe.lastfmchallenge.data.models.artists

import com.google.gson.annotations.SerializedName

data class ArtistResponse(@SerializedName("results") val results: ArtistResults)