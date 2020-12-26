package com.baha.androidfundamental

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baha.androidfundamental.data.Movie
import kotlinx.coroutines.launch

class MoviesListViewModel(private val repository: MoviesRepository):ViewModel() {

private val _mutableMovieList = MutableLiveData<List<Movie>> (emptyList())
    val movieList get() = _mutableMovieList

    fun loadMovieJson(){
        viewModelScope.launch {
            val movies = repository.getMovies()
            movieList.value = movies
        }
    }
}