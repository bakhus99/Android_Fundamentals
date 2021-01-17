package com.baha.androidfundamental.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(@Query("api_key") api: String): MoviesData

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") api: String): GenreData

    @GET("configuration")
    suspend fun getConfig(@Query("api_key") api: String): Configure

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") id: Int?,
        @Query("api_key") api: String
    ): MovieJsonModel

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") id: Int,
        @Query("api_key") api: String
    ): ActorsData
}