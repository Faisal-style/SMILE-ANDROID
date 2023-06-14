package com.example.smiletryone.data.remote.responses

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(

	@field:SerializedName("userResult")
	val userResult: UserResult,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class UserResult(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("userName")
	val userName: String,

	@field:SerializedName("email")
	val email: String
)
