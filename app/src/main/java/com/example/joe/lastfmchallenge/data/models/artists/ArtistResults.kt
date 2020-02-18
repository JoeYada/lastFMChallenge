package com.example.joe.lastfmchallenge.data.models.artists

import com.google.gson.annotations.SerializedName

class ArtistResults(
    @SerializedName("artistmatches") val artistmatches: Artistmatches
)