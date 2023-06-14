package com.example.smiletryone.data.remote.responses

import com.google.gson.annotations.SerializedName

data class SendChatResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("chatResult")
	val chatResult: ChatResult,

	@field:SerializedName("status")
	val status: String
)

data class ChatResult(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("id_conversation")
	val idConversation: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("reply")
	val reply: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
