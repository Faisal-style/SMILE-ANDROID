package com.example.smiletryone.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.smiletryone.data.remote.responses.RegisterResponse
import com.example.smiletryone.repository.SmileRepository
import com.example.smiletryone.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val smileRepository: SmileRepository
) : ViewModel() {
    var isLoading = mutableStateOf(false)

    suspend fun getRegisterInfo(
        email: String,
        password: String,
        username: String
    ): Resource<RegisterResponse> {
        isLoading.value = true
        val response = smileRepository.getRegister(email, password, username)
        delay(1000)
        isLoading.value = false
        return response
    }
}