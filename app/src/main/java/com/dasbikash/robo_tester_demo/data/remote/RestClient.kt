package com.dasbikash.robo_tester_demo.data.remote

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    //GitHub repo : https://github.com/HackerNews/API
    private const val BASE_URL = "https://hacker-news.firebaseio.com/"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient
            .Builder()
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }
}