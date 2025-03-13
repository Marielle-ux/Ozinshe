package com.example.ozinshe.presentation.about

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.model.ApiService
import com.example.ozinshe.data.model.MovieByIdResponse
import com.example.ozinshe.data.model.MovieIdModel
import com.example.ozinshe.data.model.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AboutViewModel : ViewModel() {

    private var _favoriteState: MutableLiveData<Boolean> = MutableLiveData()
    val favoriteState: MutableLiveData<Boolean> = _favoriteState

    private var _moviesByIdResponse: MutableLiveData<MovieByIdResponse> = MutableLiveData()
    val moviesByIdResponse: MutableLiveData<MovieByIdResponse> = _moviesByIdResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    private var _moviesAddResponse: MutableLiveData<MovieIdModel> = MutableLiveData()
    val moviesAddResponse: MutableLiveData<MovieIdModel> = _moviesAddResponse

    private var _moviesDeleteResponse: MutableLiveData<String> = MutableLiveData()
    val moviesDeleteResponse: MutableLiveData<String> = _moviesDeleteResponse


    fun getMovieById(token: String, movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.getMovieById("Bearer $token", movieId) }
                .onSuccess {
                    moviesByIdResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }

    fun addFavorite(token: String, movieId: MovieIdModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.addFavorite("Bearer $token", movieId) }
                .onSuccess {
                    Log.d("AAA", "addFav: ${it.movieId}")
                    _favoriteState.postValue(true)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }

        }
    }

    fun deleteFavorite(token: String, movieId: MovieIdModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.deleteFavorite("Bearer $token", movieId) }
                .onSuccess {
                    Log.d("AAA", "deleteFavorite: ${it}")
                    _favoriteState.postValue(false)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}