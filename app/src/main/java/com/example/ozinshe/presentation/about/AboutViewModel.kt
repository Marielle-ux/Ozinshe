package com.example.ozinshe.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.model.ApiService
import com.example.ozinshe.data.model.MainMovieResponseItem
import com.example.ozinshe.data.model.MovieByIdResponse
import com.example.ozinshe.data.model.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AboutViewModel : ViewModel() {

    private var _moviesByIdResponse: MutableLiveData<MovieByIdResponse> = MutableLiveData()
    val moviesByIdResponse: MutableLiveData<MovieByIdResponse> = _moviesByIdResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse


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
}