package com.baha.androidfundamental

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baha.androidfundamental.data.Movie
import com.baha.androidfundamental.models.MoviesDetailsViewModel
import com.baha.androidfundamental.repositories.ActorsRepository

class MoviesDetailsFactory(
    private val movie: Movie, private val actorsRepository: ActorsRepository,
    private val id: Int
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesDetailsViewModel::class.java -> MoviesDetailsViewModel(movie, actorsRepository,id)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}