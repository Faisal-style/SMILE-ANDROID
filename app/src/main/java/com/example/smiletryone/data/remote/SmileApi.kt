package com.example.smiletryone.data.remote

import com.example.smiletryone.data.remote.responses.LoginResponse
import com.example.smiletryone.data.remote.responses.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SmileApi {

    @FormUrlEncoded
    @POST("auth/signin")
    suspend fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("auth/signup")
    suspend fun postRegist(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("userName") username: String
    ): RegisterResponse
}