package com.baha.androidfundamental.data

import kotlinx.serialization.Serializable

@Serializable
data class Configure(
    val images: Images
)

@Serializable
data class Images(
    val base_url: String
)