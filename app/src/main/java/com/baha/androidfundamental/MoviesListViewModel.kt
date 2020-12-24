package com.baha.androidfundamental

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baha.androidfundamental.data.Movie
import com.baha.androidfundamental.data.loadMovies
import kotlinx.coroutines.launch

class MoviesListViewModel:ViewModel() {

private val _mutableMovieList = MutableLiveData<List<Movie>> (emptyList())
    val movieList get() = _mutableMovieList

    suspend fun loadMovieJson(context: Context){
        viewModelScope.launch {
            val movies = loadMovies(context)
            movieList.value = movies
        }
    }
}