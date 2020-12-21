package com.baha.androidfundamental.data

import android.os.Parcelable
import com.android.academy.fundamentals.homework.features.data.Genre
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val minimumAge: Boolean,
    val runtime: Int,
    val genres: @RawValue List<Genre>,
    val actors: @RawValue List<Actor>
) : Parcelable