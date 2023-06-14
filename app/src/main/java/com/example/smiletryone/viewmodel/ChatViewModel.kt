package com.example.smiletryone.viewmodel

import android.os.Message
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smiletryone.data.DataStoreRepository
import com.example.smiletryone.data.remote.responses.ChatResult
import com.example.smiletryone.data.remote.responses.DataItem
import com.example.smiletryone.data.remote.responses.GetChatResponse
import com.example.smiletryone.repository.SmileRepository
import com.example.smiletryone.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val smileRepository: SmileRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    var token = mutableStateOf("")
    private val _chatItems: MutableStateFlow<List<DataItem>> = MutableStateFlow(emptyList())
    val chatItems: StateFlow<List<DataItem>> = _chatItems.asStateFlow()


    init {
        viewModelScope.launch {
            dataStoreRepository.readUserToken().collect { userToken ->
                if (userToken != null) {
                    token.value = userToken
                }
            }
        }
    }

    fun getChat() {
        viewModelScope.launch {
            val response = smileRepository.getChat("Bearer ${token.value}")
            when (response) {
                is Resource.Success -> {
                    _chatItems.value = response.data?.data ?: emptyList()
                }
                else -> {}
            }
        }
    }

    fun sendChat(message: String) {
        viewModelScope.launch {
            val response = smileRepository.sendChat("Bearer ${token.value}", message)
            when (response) {
                is Resource.Success -> {
                    val newChat = response.data
                    if (newChat != null) {
                        val newDataItem = DataItem(
                            createdAt = newChat.chatResult.createdAt,
                            question = newChat.chatResult.question,
                            sender = newChat.chatResult.sender,
                            id = newChat.chatResult.id,
                            reply = newChat.chatResult.reply,
                            updatedAt = newChat.chatResult.updatedAt
                        )
                        _chatItems.value = _chatItems.value + newDataItem
                    }
                }
                else -> {}
            }
        }
    }


}