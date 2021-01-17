package com.baha.androidfundamental.repositories

import com.baha.androidfundamental.data.Actor

interface ActorsRepository {
    suspend fun getActors(id: Int): List<Actor>
}