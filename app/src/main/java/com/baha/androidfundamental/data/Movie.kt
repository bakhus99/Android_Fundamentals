package com.baha.androidfundamental.data

import com.android.academy.fundamentals.homework.features.data.Genre


data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val minimumAge: Boolean,
    val runtime: Int,
    val genres: List<Genre>,
    val actors: List<Actor>
)