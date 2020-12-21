package com.android.academy.fundamentals.homework.features.data

data class Genre(val id: Int, val name: String){
    override fun toString(): String {
        return name
    }
}