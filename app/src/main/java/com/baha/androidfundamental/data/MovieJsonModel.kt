package com.baha.androidfundamental.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieJsonModel(
    val id: Int,
    val adult: Boolean,
    val title: String,
    val genres: List<Genre>,
    @SerialName("poster_path")
    val poster: String,
    @SerialName("backdrop_path")
    val backdrop: String,
    val overview: String,
    @SerialName("vote_average")
    val ratings: Float,
    val runtime: Int,
    @SerialName("vote_count")
    val voteCount: Int
)

fun MovieJsonModel.convertToModel(
    imageBaseUrl: String
): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        poster = imageBaseUrl + "original" + this.poster,
        backdrop = imageBaseUrl + "original" + this.backdrop,
        ratings = this.ratings,
        numberOfRatings = this.voteCount,
        minimumAge = this.adult,
        runtime = this.runtime,
        genres = this.genres,
        actors = emptyList()
    )
}