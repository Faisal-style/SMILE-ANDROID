package com.example.smiletryone.data.remote.responses

import com.google.gson.annotations.SerializedName

data class GetChatResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("chatResult")
	val chatResult: List<ChatResultItem>,

	@field:SerializedName("status")
	val status: String
)

data class ChatResultItem(

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("id_conversation")
	val idConversation: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("reply")
	val reply: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
