package com.example.ozinshe.data.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.ApiService
import com.example.ozinshe.data.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _signUpResponse = MutableLiveData<SignUpResponse>()
    val signUpResponse: LiveData<SignUpResponse> get() = _signUpResponse

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> get() = _errorResponse


    fun signUpUser(email: String, password: String) {
        val response = ServiceBuilder.buildService(ApiService::class.java)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { response.signUpUser(SignUpRequest(email, password)) }
                .onSuccess { result: SignUpResponse ->
                    _signUpResponse.postValue(result)
                    Log.d("SignUpViewModel", "Email: $email, Password: $password, Result: $result")
                }
                .onFailure { throwable: Throwable ->
                    _errorResponse.postValue(throwable.message)
                    Log.d("SignUpViewModel", "Failure: ${throwable}")
                }
        }
    }
}

