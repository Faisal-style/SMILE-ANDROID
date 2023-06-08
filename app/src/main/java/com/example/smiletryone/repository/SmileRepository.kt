package com.example.smiletryone.repository

import com.example.smiletryone.data.remote.SmileApi
import com.example.smiletryone.data.remote.responses.LoginResponse
import com.example.smiletryone.data.remote.responses.RegisterResponse
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
}