package com.baha.androidfundamental.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesJsonModel(
    val id: Int,
)

@Serializable
data class MoviesData(
    val page: Int,
    @SerialName("results")
    val movies: List<MoviesJsonModel>,
    val total_pages: Int,
    val total_results: Int
)