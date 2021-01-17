package com.baha.androidfundamental.repositories

import com.baha.androidfundamental.data.Actor
import com.baha.androidfundamental.data.MovieApi
import com.baha.androidfundamental.data.covertModel

class NetworkActorsRepository(private val movieApi: MovieApi) : ActorsRepository {

    override suspend fun getActors(id: Int): List<Actor> {
        val imageBaseUrl = movieApi.getConfig("8f43ee4b8e24bbbcb9e8c7efc02e8879").images.base_url
        val actorsFromJson = movieApi.getActors(id, "8f43ee4b8e24bbbcb9e8c7efc02e8879").actors
        return actorsFromJson.map {
            it.covertModel(imageBaseUrl)
        }
    }
}