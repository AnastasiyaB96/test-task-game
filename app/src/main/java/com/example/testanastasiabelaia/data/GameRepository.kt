package com.example.testanastasiabelaia.data

import android.content.SharedPreferences
import android.util.Log
import com.example.testanastasiabelaia.LoadingResult
import com.example.testanastasiabelaia.models.ResponseModel
import retrofit2.Response

object GameRepository {
    private val service = RetrofitService.getInstance()
    private const val resultKey = "plGame"

    suspend fun getShowGame(preferences: SharedPreferences): LoadingResult {
        getShowGameLocally(preferences)?.let {
            return it
        }
        val response = service.getShowGame()
        val result = if (response.isSuccessful) {
            if (response.body()?.args?.key == "showGame") {
                LoadingResult.SHOW_GAME
            } else {
                LoadingResult.SHOW_WEB_VIEW
            }
        } else {
            Log.e("GameRepository", "Error in downloading data: ${response.message()}")
            LoadingResult.ERROR
        }
        cacheResult(preferences, result)
        return result
    }

    private fun getShowGameLocally(preferences: SharedPreferences): LoadingResult? {
        val value = preferences.getInt(resultKey, -1)
        return LoadingResult.fromValue(value)
    }

    private fun cacheResult(preferences: SharedPreferences, result: LoadingResult) {
        preferences.edit().putInt(resultKey, result.value).apply()
    }
}