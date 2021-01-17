package com.baha.androidfundamental.repositories

import com.baha.androidfundamental.data.Movie
import com.baha.androidfundamental.data.MovieApi
import com.baha.androidfundamental.data.convertToModel

class NetworkMovieRepository(private val movieApi: MovieApi) : MoviesRepository {
    override suspend fun getMovies(): List<Movie> {
        val moviesJsonModel = movieApi.getNowPlayingMovie("8f43ee4b8e24bbbcb9e8c7efc02e8879").movies
        return moviesJsonModel.map {
            getMovie(it.id)
        }
    }

    override suspend fun getMovie(id: Int): Movie {
        val movie = movieApi.getMovie(id,"8f43ee4b8e24bbbcb9e8c7efc02e8879")
        val imageBaseUrl = movieApi.getConfig("8f43ee4b8e24bbbcb9e8c7efc02e8879").images.base_url
        return movie.convertToModel(imageBaseUrl)
    }
}