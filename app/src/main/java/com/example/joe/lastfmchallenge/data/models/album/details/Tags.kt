package com.example.joe.lastfmchallenge.data.models.album.details

import com.google.gson.annotations.SerializedName

data class Tags(@SerializedName("tag")
           val tag:List<Tag>)