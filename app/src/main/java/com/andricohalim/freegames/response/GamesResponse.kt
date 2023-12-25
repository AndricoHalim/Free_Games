package com.andricohalim.freegames.response

import com.google.gson.annotations.SerializedName

data class GamesResponse(

	@field:SerializedName("end_date")
	val endDate: String,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("instructions")
	val instructions: String,

	@field:SerializedName("thumbnail")
	val thumbnail: String,

	@field:SerializedName("open_giveaway")
	val openGiveaway: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("gamerpower_url")
	val gamerpowerUrl: String,

	@field:SerializedName("open_giveaway_url")
	val openGiveawayUrl: String,

	@field:SerializedName("users")
	val users: Int,

	@field:SerializedName("worth")
	val worth: String,

	@field:SerializedName("platforms")
	val platforms: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("published_date")
	val publishedDate: String,

	@field:SerializedName("status")
	val status: String
)
