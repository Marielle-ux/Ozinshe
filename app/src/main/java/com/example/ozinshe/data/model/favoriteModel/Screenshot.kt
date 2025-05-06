package com.example.ozinshe.data.model.favoriteModel


import com.google.gson.annotations.SerializedName

data class Screenshot(
    @SerializedName("fileId")
    val fileId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("movieId")
    val movieId: Int
)