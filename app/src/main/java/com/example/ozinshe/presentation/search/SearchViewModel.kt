package com.example.ozinshe.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.model.ApiService
import com.example.ozinshe.data.model.SearchResponseModel
import com.example.ozinshe.data.model.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private var _searchMovies: MutableLiveData<SearchResponseModel> = MutableLiveData()
    val searchMovies: MutableLiveData<SearchResponseModel> = _searchMovies

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse


    fun getSearchMovie(token: String, search: String, movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.getSearchMovie("Bearer $token","{}","{}","{}",search) }
                .onSuccess {
                    Log.d("SearchViewModel", "getSearchMovie: $it")
                    _searchMovies.postValue(it)
                }
                .onFailure {
                    Log.d("SearchViewModel", "getSearchMovie: $it")
                    _errorResponse.postValue(it.message)
                }
        }
    }
}