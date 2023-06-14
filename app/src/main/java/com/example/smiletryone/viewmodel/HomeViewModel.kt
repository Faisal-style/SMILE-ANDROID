package com.example.smiletryone.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smiletryone.data.DataStoreRepository
import com.example.smiletryone.data.remote.responses.DetailUserResponse
import com.example.smiletryone.repository.SmileRepository
import com.example.smiletryone.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val smileRepository: SmileRepository
) : ViewModel() {

    private val _userToken: MutableState<String> = mutableStateOf("")
     val userToken: State<String> = _userToken

    private val _userId: MutableState<Int?> = mutableStateOf(null)
    val userId: State<Int?> = _userId

    fun saveUserToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveUserToken(token = token)
        }
    }
    fun saveUserId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveUserId(id = id)
        }
    }

    init {
        viewModelScope.launch {
            combine(
                dataStoreRepository.readUserId(),
                dataStoreRepository.readUserToken()
            ) { userId, userToken ->
                userId to userToken
            }.collect { (userId, userToken) ->
                if (userId != null) {
                    _userId.value = userId
                }
                if (userToken != null) {
                    _userToken.value = userToken
                }
            }
        }
    }

    suspend fun getUserDetailInfo(): Resource<DetailUserResponse> {
        return smileRepository.getDetailUser("Bearer ${userToken.value}", "${userId.value}")
    }

}