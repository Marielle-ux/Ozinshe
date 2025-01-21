package com.example.ozinshe.data.model

import com.google.gson.annotations.SerializedName


data class LoginRequest(
    @SerializedName("email")
    val email: String, //"email": "Star6666@gmail.com"
    @SerializedName("password")
    val password: String //qwerty
)