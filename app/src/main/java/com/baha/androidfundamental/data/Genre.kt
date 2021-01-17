package com.baha.androidfundamental.data

import kotlinx.serialization.Serializable

@Serializable
data class Genre(val id: Int, val name: String) {
    override fun toString(): String {
        return name
    }
}

@Serializable
data class GenreData(
    val genres: List<Genre>
)