package com.baha.androidfundamental.repositories

import com.baha.androidfundamental.BuildConfig
import com.baha.androidfundamental.data.Actor
import com.baha.androidfundamental.data.MovieApi
import com.baha.androidfundamental.data.covertModel

class NetworkActorsRepository(private val movieApi: MovieApi) : ActorsRepository {

    override suspend fun getActors(id: Int): List<Actor> {
        val imageBaseUrl = movieApi.getConfig(BuildConfig.API_KEY).images.base_url
        val actorsFromJson = movieApi.getActors(id, BuildConfig.API_KEY).actors
        return actorsFromJson.map {
            it.covertModel(imageBaseUrl)
        }
    }
}