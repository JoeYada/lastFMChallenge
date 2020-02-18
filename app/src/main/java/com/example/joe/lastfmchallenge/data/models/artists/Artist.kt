package com.example.joe.lastfmchallenge.data.models.artists

import com.example.joe.lastfmchallenge.data.models.Image
import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("name")  val name:String,
@SerializedName("listeners")  val listeners:String,
@SerializedName("mbid")  val mbid :String,
@SerializedName("url")  val url:String,
@SerializedName("streamable")  val  streamable:String,
@SerializedName("image")  val image: List<Image>)