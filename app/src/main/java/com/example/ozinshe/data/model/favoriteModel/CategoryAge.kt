package com.example.ozinshe.data.model.favoriteModel


import com.google.gson.annotations.SerializedName

data class CategoryAge(
    @SerializedName("fileId")
    val fileId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("movieCount")
    val movieCount: Any,
    @SerializedName("name")
    val name: String
)