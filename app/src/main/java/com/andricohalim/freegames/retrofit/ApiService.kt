package com.andricohalim.freegames.retrofit

import com.andricohalim.freegames.response.GamesResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("giveaways")
    suspend fun getStories(): List<GamesResponse>
}