package com.example.ozinshe.data.model

import com.example.ozinshe.data.model.favoriteModel.FavoriteListResponse
import com.example.ozinshe.data.signup.SignUpRequest
import com.example.ozinshe.data.signup.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/core/V1/movies_main")
    suspend fun getMainMovies(@Header("Authorization") token: String): MainMoviesResponse

    @POST("/auth/V1/signin")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

    @POST("/auth/V1/signup")
    suspend fun signUpUser(@Body signUpRequest: SignUpRequest): SignUpResponse

    @GET("/core/V1/movies/main")
    suspend fun getMainMovieByCategory(@Header("Authorization") token: String): MoviesByCategoryMainModel

    @GET("/core/V1/movies/{id}")
    suspend fun getMovieById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): MovieByIdResponse

    @GET("/core/V1/seasons/{id}")
    suspend fun getSeries(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): List<VideoResponse>

    @GET("/core/V1/favorite/")
    suspend fun getFavoriteList(@Header("Authorization") token: String):FavoriteListResponse

    @POST("/core/V1/favorite")
    suspend fun addFavorite(
        @Header("Authorization") token: String,
        @Body movieId: MovieIdModel
    ): FavoriteResponse

    @HTTP(method = "DELETE", path = "/core/V1/favorite/", hasBody = true)
    suspend fun deleteFavorite(
        @Header("Authorization") token: String,
        @Body movieId: MovieIdModel
    )

    @GET("/core/V1/movies/search")
    suspend fun getSearchMovie(
        @Header("Authorization") token: String,
        @Query("credentials") credentials: String,
        @Query("details") details: String,
        @Query("principal") principal: String,
        @Query("search") search: String
    ): SearchResponseModel
}

