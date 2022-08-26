package com.example.testanastasiabelaia.data

import com.example.testanastasiabelaia.models.ResponseModel
import retrofit2.Response

object GameRepository {
    private val service = RetrofitService.getInstance()

    suspend fun getShowGame(): Response<ResponseModel> {
        return service.getShowGame()
    }
}