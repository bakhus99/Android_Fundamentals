package com.baha.androidfundamental.repositories

import com.baha.androidfundamental.data.Movie

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
    suspend fun getMovie(id: Int):Movie
}