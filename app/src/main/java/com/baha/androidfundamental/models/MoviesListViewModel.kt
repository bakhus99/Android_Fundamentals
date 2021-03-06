package com.baha.androidfundamental.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baha.androidfundamental.data.Movie
import com.baha.androidfundamental.repositories.MoviesRepository
import kotlinx.coroutines.launch

class MoviesListViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _mutableMovieList = MutableLiveData<List<Movie>>(emptyList())
    val movieList : LiveData<List<Movie>> = _mutableMovieList

    private val _isLoading = MutableLiveData(false)
    val isLoading : LiveData<Boolean> = _isLoading

    fun loadMovieJson() {
        if (movieList.value == null) {
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            val movies = repository.getMovies()
            _isLoading.value = false
            _mutableMovieList.value = movies
        }
    }
}