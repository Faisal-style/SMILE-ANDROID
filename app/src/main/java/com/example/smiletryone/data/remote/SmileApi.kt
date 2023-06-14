package com.example.smiletryone.data.remote

import com.example.smiletryone.data.remote.responses.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

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

    @GET("user/{id}")
    suspend fun postDetailUser(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): DetailUserResponse

    @FormUrlEncoded
    @POST("chat")
    suspend fun sendChat(
        @Header("Authorization") token: String,
        @Field("question") question: String
    ): ChatResponse

    @GET("chat")
    suspend fun getChat(
        @Header("Authorization") token: String,
    ): GetChatResponse
}