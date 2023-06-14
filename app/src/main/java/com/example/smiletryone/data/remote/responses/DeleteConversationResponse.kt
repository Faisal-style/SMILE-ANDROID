package com.example.smiletryone.data.remote.responses

import com.google.gson.annotations.SerializedName

data class DeleteConversationResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
