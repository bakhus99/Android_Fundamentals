package com.baha.androidfundamental.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object NetworkModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private val contentType = "application/json".toMediaType()
    private val okHttpClients = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }
     val api: MovieApi by lazy {
        Retrofit.Builder()
            .client(okHttpClients)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(MovieApi::class.java)
    }
}

