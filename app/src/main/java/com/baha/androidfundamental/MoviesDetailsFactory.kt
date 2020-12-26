package com.baha.androidfundamental

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baha.androidfundamental.data.Movie

class MoviesDetailsFactory(private val repository: MoviesRepository, private val movie: Movie) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesDetailsViewModel::class.java -> MoviesDetailsViewModel(repository,movie)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}