package com.example.smiletryone.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smiletryone.data.DataStoreRepository
import com.example.smiletryone.data.remote.responses.DetailUserResponse
import com.example.smiletryone.navigation.Screen
import com.example.smiletryone.repository.SmileRepository
import com.example.smiletryone.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val smileRepository: SmileRepository
) : ViewModel() {

    private val _userToken: MutableState<String> = mutableStateOf("")
    private val userToken: State<String> = _userToken

    private val _route: MutableState<String> = mutableStateOf("")
    val route: State<String> = _route

    private val _conversationId: MutableState<Int?> = mutableStateOf(null)

    var isLoading = mutableStateOf(false)

    fun saveUserToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveUserToken(token = token)
        }
    }

    fun saveConversationId(conversationId: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (conversationId != null) {
                dataStoreRepository.saveConversationId(conversationId)
            }
        }
    }

    init {
        viewModelScope.launch {
            combine(
                dataStoreRepository.readConversationId(),
                dataStoreRepository.readUserToken()
            ) { conversationId, userToken ->
                conversationId to userToken
            }.collect { (conversationId, userToken) ->
                if (conversationId != null) {
                    _conversationId.value = conversationId
                }
                if (userToken != null) {
                    _userToken.value = userToken
                }
            }
        }
    }

    suspend fun getUserDetailInfo(): Resource<DetailUserResponse> {
        isLoading.value = true
        val response = smileRepository.getDetailUser("Bearer ${userToken.value}")
        isLoading.value = false
        return response
    }

    suspend fun logout() {
        isLoading.value = true
        saveUserToken("")
        saveConversationId(0)
        delay(1000)
        isLoading.value = false
        _route.value = Screen.Login.route
    }

}