package com.baha.androidfundamental.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllActors(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val actorPicture: String
)
