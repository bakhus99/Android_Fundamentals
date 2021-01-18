package com.baha.androidfundamental.repositories

import com.baha.androidfundamental.data.Actor

class AssettActorRepo(val repository: MoviesRepository) : ActorsRepository {
    override suspend fun getActors(id: Int): List<Actor> {
        return repository.getMovie(id).actors
    }
}