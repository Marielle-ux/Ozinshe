package com.example.ozinshe.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id: Int, // 25882
    @SerializedName("username")
    val username: String, // Star6666@gmail.com
    @SerializedName("email")
    val email: String, // Star6666@gmail.com
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("accessToken")
    val accessToken: String, // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJTdGFyNjY2NkBnbWFpbC5jb20iLCJpYXQiOjE3Mzc0Nzg4OTQsImV4cCI6MTc2OTAxNDg5NH0.yeb9PfXzw_dHXcTKVtPYv8g1hISJ3G0nrNk6uY6fkJ3360rOxE54vDYcc5XBTvcdmF7a9qEQU47y1n11mcVUCg
    @SerializedName("tokenType")
    val tokenType: String // Bearer
)