package com.example.premierleaguefixtures.data.retrofit

import com.example.premierleaguefixtures.data.models.MatchesArrayList
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {
    @GET("feed/json/epl-2021")
    suspend fun getAllMatchesItems(): Response<MatchesArrayList>
}