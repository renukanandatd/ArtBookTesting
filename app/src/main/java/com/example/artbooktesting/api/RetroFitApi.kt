package com.example.artbooktesting.api

import com.example.artbooktesting.model.ImageResponse
import com.example.artbooktesting.util.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroFitApi {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuery:String,
        @Query("key") apiKey:String = API_KEY
    ) : Response<ImageResponse>
}