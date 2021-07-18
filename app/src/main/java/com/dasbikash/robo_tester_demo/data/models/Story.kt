package com.dasbikash.robo_tester_demo.data.models

import com.google.gson.annotations.SerializedName

data class Story(

	@field:SerializedName("score")
	val score: Int? = null,

	@field:SerializedName("by")
	val userId: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("time")
	val time: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
