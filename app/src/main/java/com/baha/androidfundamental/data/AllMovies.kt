package com.baha.androidfundamental.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllMovies(
    val dates: Dates,
    val page: Int,
    @SerialName("results")
    val movie: List<MovieJsonModel>,
    val totalPages: Int,
    val totalResults: Int

)

@Serializable
data class Dates(
    val maximum: String,
    val minimum: String
)

@Serializable
data class AllActorsData(
    val id: Int,
    @SerialName("cast")
    val actors: List<AllActors>
)

@Serializable
data class AllGenresData(
    val genres: List<com.baha.androidfundamental.data.Genre>
)

@Serializable
data class Genre(
    val id: Int,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}

@Serializable
data class Configure(
    val images: Images
)

@Serializable
data class Images(
    val base_url: String
)