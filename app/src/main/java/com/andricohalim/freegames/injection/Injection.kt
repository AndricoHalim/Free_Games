package com.andricohalim.freegames.injection

import android.content.Context
import com.andricohalim.freegames.repository.GamesRepository
import com.andricohalim.freegames.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): GamesRepository {
        val apiService = ApiConfig.getApiService()
        return GamesRepository(apiService)
    }

}