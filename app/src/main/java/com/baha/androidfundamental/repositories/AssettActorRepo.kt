package com.baha.androidfundamental.repositories

import com.baha.androidfundamental.data.Actor

class AssettActorRepo(val repo: MoviesRepository) : ActorsRepository {
    override suspend fun getActors(id: Int): List<Actor> {
        return repo.getMovie(id).actors
    }
}