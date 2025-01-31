package com.example.ozinshe.data.model


import com.google.gson.annotations.SerializedName

data class MainMovieResponseItem(
    @SerializedName("id")
    val id: Int, // 41
    @SerializedName("link")
    val link: String, // http://api.ozinshe.com/core/public/V1/show/663
    @SerializedName("fileId")
    val fileId: Int, // 663
    @SerializedName("movie")
    val movie: Movie,
    @SerializedName("sortOrder")
    val sortOrder: Int // 1
)