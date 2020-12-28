package com.baha.androidfundamental

import com.baha.androidfundamental.data.Movie

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
}