package com.example.joe.lastfmchallenge.data.models

import com.google.gson.annotations.SerializedName

data class OpensearchQuery(@SerializedName("#text")val text:String,
                           @SerializedName("role") val role:String,
                           @SerializedName("searchTerms")val searchTerms:String,
                           @SerializedName("startPage")val startPage:String)