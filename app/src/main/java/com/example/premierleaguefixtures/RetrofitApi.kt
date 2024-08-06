package com.example.premierleaguefixtures

import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {
    @GET("feed/json/epl-2021")
    suspend fun getData(): Response<List<MatchItem>>
}