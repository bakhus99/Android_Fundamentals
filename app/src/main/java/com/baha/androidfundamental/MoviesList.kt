package com.baha.androidfundamental

import com.baha.androidfundamental.data.Movie

object MoviesList {
    fun getMovieList(): List<Movie> = listOf(
        Movie(
           "Avengers: End Game",
            "137 min",
           "Action, Adventure, Fantasy",
            "125 Reviews",
            "13+",
            R.drawable.avg
        ), Movie(
            "Tenet",
            "97 min",
            "Action, Adventure, Fantasy",
            "98 reviews",
            "16+",
            R.drawable.tenet
        ), Movie(
            "Black Widow",
            "102 min",
            "Action, Adventure, Fantasy",
            "38 reviews",
            "13+",
            R.drawable.bw
        ), Movie(
            "Wonder Woman 1984",
            "120 min",
            "Action, Adventure, Fantasy",
            "74 reviews",
            "13+",
            R.drawable.ww
        )
    )
}