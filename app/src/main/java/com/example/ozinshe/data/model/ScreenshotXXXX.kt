package com.example.ozinshe.data.model


import com.google.gson.annotations.SerializedName

data class ScreenshotXXXX(
    @SerializedName("id")
    val id: Int, // 154
    @SerializedName("link")
    val link: String, // http://api.ozinshe.com/core/public/V1/show/632
    @SerializedName("fileId")
    val fileId: Int, // 632
    @SerializedName("movieId")
    val movieId: Int // 121
)