package com.example.ozinshe.data.signup


import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("id")
    val id: Int, // 25882
    @SerializedName("username")
    val username: String, // Star6666@gmail.com
    @SerializedName("email")
    val email: String, // Star6666@gmail.com
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("accessToken")
    val accessToken: String, // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJTdGFyNjY2NkBnbWFpbC5jb20iLCJpYXQiOjE3Mzc0Nzg4NjAsImV4cCI6MTc2OTAxNDg2MH0.uccdf9rk2gbp30xAyafCQD27uh3JD1yHRJaimWM1woL1fWmAAarIiLuglDeENSRlNWpZk2J7PSNWlZs8_lC0RA
    @SerializedName("tokenType")
    val tokenType: String // Bearer
)