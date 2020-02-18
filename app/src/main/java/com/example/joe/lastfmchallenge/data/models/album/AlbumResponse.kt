package com.example.joe.lastfmchallenge.data.models.album

import com.google.gson.annotations.SerializedName

data class AlbumResponse(@SerializedName("results")val results: Results)