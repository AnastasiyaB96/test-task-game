package com.example.testanastasiabelaia.data

import com.example.testanastasiabelaia.models.ResponseModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("get?key=showGame")
    suspend fun getShowGame(): Response<ResponseModel>

    companion object {
        private const val BASE_URL = "https://google.apimetrics.xyz/"

        private var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}