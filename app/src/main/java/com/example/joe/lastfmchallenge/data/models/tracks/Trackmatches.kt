package com.example.joe.lastfmchallenge.data.models.tracks

import com.google.gson.annotations.SerializedName

data class Trackmatches(@SerializedName("track")
                   val track:List<Track>)