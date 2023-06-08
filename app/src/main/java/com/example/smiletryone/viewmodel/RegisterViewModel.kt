package com.example.smiletryone.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.smiletryone.data.remote.responses.LoginResponse
import com.example.smiletryone.data.remote.responses.RegisterResponse
import com.example.smiletryone.repository.SmileRepository
import com.example.smiletryone.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val smileRepository: SmileRepository
) : ViewModel() {
    var loginData = mutableStateOf<RegisterResponse?>(null)

    suspend fun getRegisterInfo(
        email: String,
        password: String,
        username: String
    ): Resource<RegisterResponse> {
        return smileRepository.getRegister(email, password, username)
    }
}