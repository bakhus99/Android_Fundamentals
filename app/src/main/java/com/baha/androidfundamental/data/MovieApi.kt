package com.baha.androidfundamental.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/movie/now_playing")
    suspend fun getNowPlayingMovie(@Query("api_key") api: String): Response<List<MovieJsonModel>>
}