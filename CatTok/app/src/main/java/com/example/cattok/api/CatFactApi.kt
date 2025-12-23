package com.example.cattok.api

import com.example.cattok.data.models.CatFact
import retrofit2.http.GET

interface CatFactApi {
    @GET("fact")
    suspend fun getRandomFact(): CatFact
}
