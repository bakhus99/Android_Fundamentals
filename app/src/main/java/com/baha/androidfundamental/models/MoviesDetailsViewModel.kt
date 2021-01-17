package com.baha.androidfundamental.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baha.androidfundamental.data.Actor
import com.baha.androidfundamental.data.Movie
import com.baha.androidfundamental.repositories.ActorsRepository
import kotlinx.coroutines.launch

class MoviesDetailsViewModel(
    private val movies: Movie,
    private val actorsRepository: ActorsRepository,
    private val id: Int
) :
    ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie
    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> = _actors

    fun fetchMovie() {
        if (movie.value != null) {
            return
        }
        viewModelScope.launch {
            _movie.value = movies
            _actors.value = actorsRepository.getActors(id)
            //_actors.value = movie.value?.actors
        }
    }
}
