package com.example.smiletryone.data.remote.responses

import com.google.gson.annotations.SerializedName

data class GetChatResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("sender")
	val sender: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("reply")
	val reply: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
