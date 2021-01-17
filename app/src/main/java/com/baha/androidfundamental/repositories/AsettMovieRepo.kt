package com.baha.androidfundamental.repositories

import android.content.Context
import com.baha.androidfundamental.data.Movie
import com.baha.androidfundamental.data.loadMovies

class AsettMovieRepo(private val context: Context) : MoviesRepository {
    override suspend fun getMovies(): List<Movie> {
        return loadMovies(context)
    }

    override suspend fun getMovie(id: Int): Movie {
        return loadMovies(context).find { it.id ==id }!!
    }
}