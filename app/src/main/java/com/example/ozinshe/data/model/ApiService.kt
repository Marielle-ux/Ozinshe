package com.example.ozinshe.data.model

import com.example.ozinshe.data.signup.SignUpRequest
import com.example.ozinshe.data.signup.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

   @GET("/core/V1/movies_main")
   suspend fun getMainMovies(@Header("Authorization") token: String):MainMoviesResponse

   @POST("/auth/V1/signin")
   suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

   @POST("/auth/V1/signup")
   suspend fun signUpUser(@Body signUpRequest: SignUpRequest): SignUpResponse

   @GET ("/core/V1/movies/main")
   suspend fun getMainMovieByCategory(@Header("Authorization")token: String): MoviesByCategoryMainModel

   @GET("/core/V1/movies/{id}")
   suspend fun getMovieById(
      @Header("Authorization") token: String,
      @Path("id") id: Int
   ):MovieByIdResponse
   @GET("/core/V1/seasons/{id}")
   suspend fun getSeries(
      @Header("Authorization") token: String,
      @Path("id") id: Int
   ):List<VideoResponse>

   @POST("/core/V1/favorite")
   suspend fun addFavorite(
      @Header("Authorization") token: String,
      @Body movieBody:MovieIdModel
   ):MovieIdModel

   @HTTP(method = "DELETE", path = "/core/V1/favorite/", hasBody = true)
   suspend fun deleteFavorite(
      @Header("Authorization") token: String,
      @Body movieBody:MovieIdModel
   )
}
