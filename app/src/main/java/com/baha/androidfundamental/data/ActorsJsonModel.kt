package com.baha.androidfundamental.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorsJsonModel(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val actorPicture: String?
)

@Serializable
data class ActorsData(
    val id: Int,
    @SerialName("cast")
    val actors: List<ActorsJsonModel>
)