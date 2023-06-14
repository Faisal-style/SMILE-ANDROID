package com.example.smiletryone.repository

import com.example.smiletryone.data.remote.SmileApi
import com.example.smiletryone.data.remote.responses.*
import com.example.smiletryone.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class SmileRepository @Inject constructor(
    private val api: SmileApi
) {
    suspend fun getLogin(email: String, password: String): Resource<LoginResponse> {
        val response = try {
            api.postLogin(email, password)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred. $e")
        }
        return Resource.Success(response)
    }

    suspend fun getRegister(
        email: String,
        password: String,
        username: String
    ): Resource<RegisterResponse> {
        val response = try {
            api.postRegist(email, password, username)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred. $e")
        }
        return Resource.Success(response)
    }

    suspend fun getDetailUser(
        token: String
    ): Resource<DetailUserResponse> {
        val response = try {
            api.postDetailUser(token)
        } catch (e: Exception) {
            return Resource.Error("An Unknown error ocurred. $e")
        }
        return Resource.Success(response)
    }

    suspend fun sendChat(
        token: String,
        conversationId: String,
        message: String
    ): Resource<SendChatResponse>{
        val response = try {
            api.sendChat(token, conversationId, message)
        } catch (e: Exception){
            return Resource.Error("An Unkonwn error ocurred. $e")
        }
        return Resource.Success(response)
    }

    suspend fun getChat(
        token: String,
        conversationId : String
    ): Resource<GetChatResponse>{
        val response = try{
            api.getChat(token, conversationId)
        }catch (e: Exception){
            return Resource.Error("An Unknown error ocurred. $e")
        }
        return Resource.Success(response)
    }

    suspend fun createConversation(token: String): Resource<CreateConversationResponse>{
        val response = try{
            api.createConversation(token)
        }catch (e: Exception){
            return Resource.Error("An Unknown error ocurred. $e")
        }
        return Resource.Success(response)
    }

    suspend fun getConversation(token: String): Resource<GetConversationResponse>{
        val response = try{
            api.getConversation(token)
        }catch (e: Exception){
            return Resource.Error("An Unknown error ocurred. $e")
        }
        return Resource.Success(response)
    }

    suspend fun deleteConversation(
        token: String,
        conversationId : String
    ): Resource<DeleteConversationResponse>{
        val response = try{
            api.deleteConversation(token, conversationId)
        }catch (e: Exception){
            return Resource.Error("An Unknown error ocurred. $e")
        }
        return Resource.Success(response)
    }
}