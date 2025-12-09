package com.example.catgallery.api

import com.example.catgallery.data.models.CatFact
import retrofit2.http.GET

interface CatFactApi {
    @GET("fact")
    suspend fun getRandomFact(): CatFact
}
