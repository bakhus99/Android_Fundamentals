package com.baha.androidfundamental

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baha.androidfundamental.models.MoviesListViewModel
import com.baha.androidfundamental.repositories.MoviesRepository

class MoviesListFactory(private val repository: MoviesRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesListViewModel::class.java -> MoviesListViewModel(repository)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}