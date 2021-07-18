package com.dasbikash.robo_tester_demo.data.remote

import com.dasbikash.robo_tester_demo.data.models.Story
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsService {

    @GET("v0/{type}.json?print=pretty")
    suspend fun getStoryIdsByType(@Path("type") type: String): Response<List<Int>?>

//    @GET("v0/user/{userId}.json?print=pretty")
//    suspend fun getStoryIdsForUser(@Path("userId") userId: String): Response<List<Int>?>

    @GET("v0/item/{storyId}.json?print=pretty")
    suspend fun getStoryById(@Path("storyId") storyId: Int): Response<Story?>
}