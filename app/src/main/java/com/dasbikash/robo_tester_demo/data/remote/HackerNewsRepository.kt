package com.dasbikash.robo_tester_demo.data.remote

import com.dasbikash.robo_tester_demo.data.models.Story
import com.dasbikash.robo_tester_demo.data.models.StoryType

class HackerNewsRepository() {
    private val apiClient = RestClient.retrofit.create(HackerNewsService::class.java)

    suspend fun getStoryIdsByType(storyType: StoryType): List<Int>? =
        try {
            val response = apiClient.getStoryIdsByType(storyType.typeId)
            if (response.isSuccessful) response.body()
            else null
        } catch (ex: Throwable) {
            ex.printStackTrace()
            null
        }

//    suspend fun getStoryIdsForUser(userId: String): List<Int>? =
//        try {
//            val response = apiClient.getStoryIdsForUser(userId)
//            if (response.isSuccessful) response.body()
//            else null
//        } catch (ex: Throwable) {
//            ex.printStackTrace()
//            null
//        }

    suspend fun getStoryById(storyId: Int): Story? =
        try {
            val response = apiClient.getStoryById(storyId)
            if (response.isSuccessful) response.body()
            else null
        } catch (ex: Throwable) {
            ex.printStackTrace()
            null
        }
}