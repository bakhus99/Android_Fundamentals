package com.baha.androidfundamental.repositories

import com.baha.androidfundamental.BuildConfig
import com.baha.androidfundamental.data.Movie
import com.baha.androidfundamental.data.MovieApi
import com.baha.androidfundamental.data.convertToModel

class NetworkMovieRepository(private val movieApi: MovieApi) : MoviesRepository {

    override suspend fun getMovies(): List<Movie> {
        val moviesToModelJson = movieApi.getNowPlayingMovie(BuildConfig.API_KEY).movies
        return moviesToModelJson.map {
            getMovie(it.id)
        }
    }

    override suspend fun getMovie(id: Int): Movie {
        val movie = movieApi.getMovie(id,BuildConfig.API_KEY)
        val imageBaseUrl = movieApi.getConfig(BuildConfig.API_KEY).images.base_url
        return movie.convertToModel(imageBaseUrl)
    }
}