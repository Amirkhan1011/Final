package com.example.cattok.api

import com.example.cattok.data.models.CatImg
import retrofit2.http.GET

interface CatImgApi {
    @GET("images/search")
    suspend fun getRandomImg(): List<CatImg>
}
