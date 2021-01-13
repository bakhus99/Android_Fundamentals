package com.baha.androidfundamental.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieJsonModel(
    val id: Int,
    val adult: Boolean,
    val genre: List<Int>,
    @SerialName("poster_path")
    val poster: String,
    @SerialName("backdrop_path")
    val backdrop: String,
    val overview: String,
    @SerialName("vote_overage")
    val ratings: Float,
    @SerialName("vote_count")
    val voteCount: String,
    )