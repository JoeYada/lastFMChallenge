package com.example.joe.lastfmchallenge.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(@SerializedName("#text") val text:String,
                 @SerializedName("size")val size:String) : Parcelable