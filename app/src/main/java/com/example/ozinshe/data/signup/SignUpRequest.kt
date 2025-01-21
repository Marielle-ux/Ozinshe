package com.example.ozinshe.data.signup


import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("email")
    val email: String, // Star6666@gmail.com
    @SerializedName("password")
    val password: String // qwerty
)