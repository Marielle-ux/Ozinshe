package com.example.ozinshe.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.model.ApiService
import com.example.ozinshe.data.model.FavoriteModelItem
import com.example.ozinshe.data.model.FavoriteResponse
import com.example.ozinshe.data.model.MovieIdModel
import com.example.ozinshe.data.model.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {

    private val service = ServiceBuilder.buildService(ApiService::class.java)

    private val _addFavoriteResponse = MutableLiveData<FavoriteResponse>()
    val addFavoriteResponse: LiveData<FavoriteResponse> = _addFavoriteResponse

    private val _deleteFavoriteResponse = MutableLiveData<FavoriteResponse>()
    val deleteFavoriteResponse: LiveData<FavoriteResponse> = _deleteFavoriteResponse

    private val _favoriteList = MutableLiveData<List<FavoriteModelItem>>()
    val favoriteList: LiveData<List<FavoriteModelItem>> = _favoriteList

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    fun getFavoriteList(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = service.getFavoriteList("Bearer $token")
                _favoriteList.postValue(result)
            } catch (e: Exception) {
                _errorResponse.postValue("Ошибка загрузки избранного: ${e.localizedMessage}")
            }
        }
    }

    fun addFavorite(token: String, movieIdModel: MovieIdModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = service.addFavorite("Bearer $token", movieIdModel)
                _addFavoriteResponse.postValue(result)
                getFavoriteList(token) // обновляем список после добавления
            } catch (e: Exception) {
                _errorResponse.postValue("Ошибка добавления в избранное: ${e.localizedMessage}")
            }
        }
    }

    fun deleteFavorite(token: String, movieIdModel: MovieIdModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = service.deleteFavorite("Bearer $token", movieIdModel)
                _deleteFavoriteResponse.postValue(result)
                getFavoriteList(token) // обновляем список после удаления
            } catch (e: Exception) {
                _errorResponse.postValue("Ошибка удаления из избранного: ${e.localizedMessage}")
            }
        }
    }
}
