package com.example.ozinshe.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.model.ApiService
import com.example.ozinshe.data.model.ServiceBuilder
import com.example.ozinshe.data.model.VideoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeriesViewModel:  ViewModel() {

    private var _videosResponse: MutableLiveData<List<VideoResponse>> = MutableLiveData()
    val videosResponse: MutableLiveData<List<VideoResponse>> = _videosResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse


    fun getMovieById(token: String, movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.getSeries("Bearer $token", movieId) }
                .onSuccess {
                    _videosResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}