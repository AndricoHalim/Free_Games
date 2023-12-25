package com.andricohalim.freegames.repository

import android.net.http.HttpException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.andricohalim.freegames.response.GamesResponse
import com.andricohalim.freegames.retrofit.ApiService
import com.andricohalim.freegames.util.Result

class GamesRepository(
    private val apiService: ApiService
) {

    fun getGames():  LiveData<Result<List<GamesResponse>>> =
        liveData {
            emit(com.andricohalim.freegames.util.Result.Loading)
            try {
                val storyResponse = apiService.getStories()
                Log.d("GamesRepository", "Number of items: ${storyResponse.size}")
                emit(Result.Success(storyResponse))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error("Terjadi kesalahan: ${e.message}"))
            }
        }
    }