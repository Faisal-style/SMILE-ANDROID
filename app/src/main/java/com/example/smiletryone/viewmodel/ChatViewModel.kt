package com.example.smiletryone.viewmodel

import android.os.Message
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smiletryone.data.DataStoreRepository
import com.example.smiletryone.data.remote.responses.ChatResult
import com.example.smiletryone.data.remote.responses.ChatResultItem
import com.example.smiletryone.data.remote.responses.DataConversation
import com.example.smiletryone.data.remote.responses.GetChatResponse
import com.example.smiletryone.repository.SmileRepository
import com.example.smiletryone.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val smileRepository: SmileRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    private val _userToken: MutableState<String> = mutableStateOf("")
    private val userToken: State<String> = _userToken

    private val _conversationId: MutableState<Int?> = mutableStateOf(null)
    val conversationId: State<Int?> = _conversationId

    private val _chatItems: MutableStateFlow<List<ChatResultItem>> = MutableStateFlow(emptyList())
    val chatItems: StateFlow<List<ChatResultItem>> = _chatItems.asStateFlow()

    private val _deleteMessage: MutableState<String> = mutableStateOf("")
    private val deleteMessage: State<String> = _deleteMessage

    private val _conversationItems: MutableStateFlow<MutableList<DataConversation>> =
        MutableStateFlow(mutableListOf())
    val conversationItems: StateFlow<MutableList<DataConversation>> = _conversationItems.asStateFlow()


    var isLoading = mutableStateOf(false)


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

    fun getChat() {
        viewModelScope.launch {
            val response =
                smileRepository.getChat("Bearer ${userToken.value}", "${conversationId.value}")
            when (response) {
                is Resource.Success -> {
                    _chatItems.value = response.data?.chatResult ?: emptyList()
                }
                else -> {}
            }
        }
    }



    fun getConversation() {
        viewModelScope.launch {
            val response = smileRepository.getConversation("Bearer ${userToken.value}")
            when (response) {
                is Resource.Success -> {
                    _conversationItems.value = response.data?.data?.toMutableList() ?: mutableListOf()
                }
                else -> {}
            }
        }
    }

    fun newConversation() {
        viewModelScope.launch {
            isLoading.value = true
            val response = smileRepository.createConversation("Bearer ${userToken.value}")
            delay(1500)
            isLoading.value = false
            when (response) {
                is Resource.Success -> {
                    _conversationId.value = response.data?.data?.id
                    conversationId.value?.let { saveConversationId(it) }
                }
                else -> {}
            }
        }
    }


    fun saveConversationId(conversationId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveConversationId(conversationId)
        }
    }

    fun deleteConversation(conversationId: Int) {
        viewModelScope.launch {
            val response = smileRepository.deleteConversation(
                "Bearer ${userToken.value}",
                conversationId = conversationId.toString()
            )
            delay(1000)
            when (response) {
                is Resource.Success -> {
                    _deleteMessage.value = response.message.toString()
                    _conversationItems.value.removeIf { it.id == conversationId }
                }
                else -> {}
            }
        }
    }

    fun sendChat(message: String) {
        viewModelScope.launch {
            val newDataItem = ChatResultItem(
                updatedAt = "",
                question = message,
                idConversation = 1000,
                reply = "",
                id = 1000
            )
            _chatItems.value = _chatItems.value + newDataItem
            val response = smileRepository.sendChat(
                "Bearer ${userToken.value}",
                "${conversationId.value}",
                message
            )
            when (response) {
                is Resource.Success -> {
                    val newChat = response.data
                    if (newChat != null) {
                        val lastIndex = _chatItems.value.lastIndex
                        if (lastIndex >= 0) {
                            val updatedItem = _chatItems.value[lastIndex].copy(
                                updatedAt = newChat.chatResult.updatedAt,
                                question = newChat.chatResult.question,
                                idConversation = newChat.chatResult.idConversation.toInt(),
                                reply = newChat.chatResult.reply,
                                id = newChat.chatResult.id
                            )
                            val updatedChatItems = _chatItems.value.toMutableList()
                            updatedChatItems[lastIndex] = updatedItem
                            _chatItems.value = updatedChatItems
                        }
                    }
                }
                else -> {}
            }
        }
    }

}