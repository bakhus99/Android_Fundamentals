package com.baha.androidfundamental

import android.content.Context
import com.baha.androidfundamental.data.Movie
import com.baha.androidfundamental.data.loadMovies

class AsettMovieRepo(val context: Context):MoviesRepository {
    override suspend fun getMovies(): List<Movie> {
        return loadMovies(context)
    }
}