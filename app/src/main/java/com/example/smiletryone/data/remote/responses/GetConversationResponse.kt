package com.example.smiletryone.data.remote.responses

import com.google.gson.annotations.SerializedName

data class GetConversationResponse(

	@field:SerializedName("data")
	val data: List<DataConversation>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataConversation(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("id_user")
	val idUser: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
