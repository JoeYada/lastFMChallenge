package com.example.joe.lastfmchallenge.data.models.album.details

import com.google.gson.annotations.SerializedName

data class Tag(@SerializedName("name")
           val name:String,
@SerializedName("url")
val url:String)