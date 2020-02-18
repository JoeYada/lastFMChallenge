package com.example.joe.lastfmchallenge.data.models.tracks

import com.google.gson.annotations.SerializedName

data class TrackResponse(@SerializedName("results") val results:TrackResults)