package com.example.catgallery.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CatVidRetrofit {
    private val logging = HttpLoggingInterceptor().apply{
        level = HttpLoggingInterceptor.Level.BODY

    }
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Ys24l7vLIyxF25Ww6j9lRtwWXD5eCDd8tJUon7FlbGX4V2zcYaxUdU2H")
                .build()
            chain.proceed(newRequest)
        }
        .addInterceptor (logging)
        .build()


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.pexels.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: CatVidApi by lazy {
        retrofit.create(CatVidApi::class.java)
    }

}