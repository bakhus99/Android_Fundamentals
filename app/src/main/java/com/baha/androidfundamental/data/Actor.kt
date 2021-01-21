package com.baha.androidfundamental.data

data class Actor(
    val id: Int,
    val name: String,
    val picture: String
)

fun ActorsJsonModel.covertModel(imageBaseUrl:String): Actor{
    return Actor(
        id = this.id, name = this.name,picture = imageBaseUrl + "original" + this.actorPicture
    )
}