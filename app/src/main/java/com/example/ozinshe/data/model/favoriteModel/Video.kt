package com.example.ozinshe.data.model.favoriteModel


import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("seasonId")
    val seasonId: Any
)