package com.example.smiletryone.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smiletryone.data.DataStoreRepository
import com.example.smiletryone.data.remote.responses.LoginResponse
import com.example.smiletryone.navigation.Screen
import com.example.smiletryone.repository.SmileRepository
import com.example.smiletryone.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataRepository: DataStoreRepository,
    private val smileRepository: SmileRepository
) : ViewModel() {
    var loginData = mutableStateOf<LoginResponse?>(null)
    var error = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    var token = mutableStateOf("")

    private val _homeDestination: MutableState<String> = mutableStateOf(Screen.SplashScreen.route)
    val homeDestination: State<String> = _homeDestination

    init {
        viewModelScope.launch {
            dataRepository.readUserToken().collect { userToken ->
                if (userToken != null) {
                    _homeDestination.value = Screen.Home.route
                    token.value = userToken
                } else {
                    _homeDestination.value = Screen.Login.route
                }

            }
        }
    }

    fun saveUserToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.saveUserToken(token = token)
        }
    }

    suspend fun getLoginInfo(email: String, password: String) {
        isLoading.value = true
        val result = smileRepository.getLogin(email, password)
        delay(1000)
        when (result) {
            is Resource.Success -> {
                loginData.value = result.data
                isLoading.value = false
            }
            is Resource.Error -> {
                error.value = result.message.toString()
                loginData.value = null
                isLoading.value = false
            }
            else -> {}
        }
    }
}