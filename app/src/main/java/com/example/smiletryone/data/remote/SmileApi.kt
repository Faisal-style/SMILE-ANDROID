package com.example.smiletryone.data.remote

import com.example.smiletryone.data.remote.responses.*
import retrofit2.http.DELETE
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

    @GET("user/detail")
    suspend fun postDetailUser(
        @Header("Authorization") token: String,
    ): DetailUserResponse

    @FormUrlEncoded
    @POST("conversation/chat/{conversationId}")
    suspend fun sendChat(
        @Header("Authorization") token: String,
        @Path("conversationId") conversationId : String,
        @Field("question") question: String
    ): SendChatResponse

    @GET("conversation/chat/{conversationId}")
    suspend fun getChat(
        @Header("Authorization") token: String,
        @Path("conversationId") conversationId: String
    ): GetChatResponse

    @POST("conversation")
    suspend fun createConversation(
        @Header("Authorization") token: String
    ): CreateConversationResponse

    @GET("conversation")
    suspend fun getConversation(
        @Header("Authorization") token: String
    ): GetConversationResponse

    @DELETE("conversation/{conversationId}")
    suspend fun deleteConversation(
        @Header("Authorization") token: String,
        @Path("conversationId") conversationId: String
    ): DeleteConversationResponse

}