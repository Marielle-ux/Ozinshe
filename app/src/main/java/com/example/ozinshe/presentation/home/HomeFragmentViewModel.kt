package com.example.ozinshe.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.model.ApiService
import com.example.ozinshe.data.model.MainMoviesResponse
import com.example.ozinshe.data.model.MoviesByCategoryMainModel
import com.example.ozinshe.data.model.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragmentViewModel : ViewModel() {
    private var _mainMoviesResponse: MutableLiveData<MainMoviesResponse> = MutableLiveData()
    val mainMoviesResponse: MutableLiveData<MainMoviesResponse> = _mainMoviesResponse

    private var _moviesByCategoryMainModel: MutableLiveData<MoviesByCategoryMainModel> = MutableLiveData()
    val moviesByCategoryMainModel: MutableLiveData<MoviesByCategoryMainModel> = _moviesByCategoryMainModel

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse


    fun getMainMovies(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.getMainMovies("Bearer $token") }
                .onSuccess {
                    _mainMoviesResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }

    fun getMoviesByCategory(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.getMainMovieByCategory("Bearer $token") }
                .onSuccess {
                    _moviesByCategoryMainModel.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}