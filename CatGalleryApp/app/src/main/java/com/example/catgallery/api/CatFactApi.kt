package com.example.catgallery.api

import com.example.catgallery.models.CatFact
import retrofit2.http.GET

interface CatFactApi {
    @GET("fact")
    suspend fun getRandomFact(): CatFact
}
