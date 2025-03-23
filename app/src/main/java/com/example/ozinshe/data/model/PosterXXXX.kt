package com.example.ozinshe.data.model


import com.google.gson.annotations.SerializedName

data class PosterXXXX(
    @SerializedName("id")
    val id: Int, // 130
    @SerializedName("link")
    val link: String, // http://api.ozinshe.com/core/public/V1/show/644
    @SerializedName("fileId")
    val fileId: Int, // 644
    @SerializedName("movieId")
    val movieId: Int // 121
)