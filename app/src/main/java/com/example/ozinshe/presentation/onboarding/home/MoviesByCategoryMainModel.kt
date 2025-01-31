package com.example.ozinshe.presentation.onboarding.home

import com.example.ozinshe.data.model.Movy

data class MoviesByCategoryMainModel(
    val categoryName: String,
    val movies: List<Movy>

)
