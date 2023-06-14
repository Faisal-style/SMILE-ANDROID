package com.example.smiletryone.data.remote.responses

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class Data(

	@field:SerializedName("image")
	val image: Any,

	@field:SerializedName("fullName")
	val fullName: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String
)
