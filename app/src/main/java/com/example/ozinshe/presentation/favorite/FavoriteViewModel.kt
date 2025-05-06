package com.example.ozinshe.presentation.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.model.ApiService
import com.example.ozinshe.data.model.FavoriteModelItem
import com.example.ozinshe.data.model.FavoriteResponse
import com.example.ozinshe.data.model.MovieIdModel
import com.example.ozinshe.data.model.ServiceBuilder
import com.example.ozinshe.data.model.favoriteModel.FavoriteListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {

    private val service = ServiceBuilder.buildService(ApiService::class.java)

    private val _addFavoriteResponse = MutableLiveData<FavoriteResponse>()
    val addFavoriteResponse: LiveData<FavoriteResponse> = _addFavoriteResponse

    private val _deleteFavoriteResponse = MutableLiveData<FavoriteResponse>()
    val deleteFavoriteResponse: LiveData<FavoriteResponse> = _deleteFavoriteResponse

    private val _favoriteList = MutableLiveData<FavoriteListResponse>()
    val favoriteList: LiveData<FavoriteListResponse> = _favoriteList

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    fun getFavoriteList(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                service.getFavoriteList("Bearer $token")
            }.onSuccess {
                    Log.d("AAA", "getFavoriteList: $it")
                    _favoriteList.postValue(it)
                }
                .onFailure {
                    Log.d("AAA", "getFavoriteList error: ${it.message}")
                    _errorResponse.postValue(it.message)
                }
        }
    }


}
