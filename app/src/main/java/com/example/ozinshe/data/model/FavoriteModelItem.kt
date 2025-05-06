package com.example.ozinshe.data.model


import com.google.gson.annotations.SerializedName

data class FavoriteModelItem(
    @SerializedName("id")
    val id: Int, // 109
    @SerializedName("movieType")
    val movieType: String, // MOVIE
    @SerializedName("name")
    val name: String, // Махамбет
    @SerializedName("keyWords")
    val keyWords: String, // Махамбет батыр
    @SerializedName("description")
    val description: String, // Махамбет
    @SerializedName("year")
    val year: Int, // 2020
    @SerializedName("trend")
    val trend: Boolean, // true
    @SerializedName("timing")
    val timing: Int, // 45
    @SerializedName("director")
    val director: String, // Қасиет Сақиолла
    @SerializedName("producer")
    val producer: String, // Қасиет Сақиолла
    @SerializedName("poster")
    val poster: PosterXXXXX,
    @SerializedName("video")
    val video: VideoXXXXX,
    @SerializedName("watchCount")
    val watchCount: Int, // 5762
    @SerializedName("seasonCount")
    val seasonCount: Int, // 0
    @SerializedName("seriesCount")
    val seriesCount: Int, // 0
    @SerializedName("createdDate")
    val createdDate: String, // 2022-01-31T05:09:15.703+00:00
    @SerializedName("lastModifiedDate")
    val lastModifiedDate: String, // 2022-07-14T05:50:03.680+00:00
    @SerializedName("screenshots")
    val screenshots: List<ScreenshotXXXXX>,
    @SerializedName("categoryAges")
    val categoryAges: List<CategoryAgeXXXXX>,
    @SerializedName("genres")
    val genres: List<GenreXXXXX>,
    @SerializedName("categories")
    val categories: List<CategoryXXXXX>,
    @SerializedName("favorite")
    val favorite: Boolean // true
)