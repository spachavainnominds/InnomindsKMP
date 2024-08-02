package org.innominds.kmplogin.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import data.remote.models.Post
import data.remote.usecases.LoginApiCall
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {

    private val _isLoginSuccess = MutableStateFlow<List<Post>>(emptyList())
    val isLoginSuccess = _isLoginSuccess.asStateFlow()


    fun login(userName: String, password: String) {
        // Call the API
        // Update the _isLoginSuccess value based on the response
        MainScope().launch {
            kotlin.runCatching {
                val response = LoginApiCall.invoke()
                _isLoginSuccess.value = response.data ?: emptyList()

            }.onSuccess { response ->
                Log.e("LoginViewModel", "LoginViewModel :: ApiServiceClient Success == ${response}")
            }.onFailure { error ->
                Log.e("LoginViewModel", "LoginViewModel :: ApiServiceClient Failure == ${error.message}")
            }

        }
    }
}