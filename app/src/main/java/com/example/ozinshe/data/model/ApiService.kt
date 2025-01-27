package com.example.ozinshe.data.model

import com.example.ozinshe.data.signup.SignUpRequest
import com.example.ozinshe.data.signup.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
   @POST("/auth/V1/signin")
   suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

   @POST("/auth/V1/signup")
   suspend fun signUpUser(@Body signUpRequest: SignUpRequest): SignUpResponse
}
