package com.example.catgallery.api

import com.example.catgallery.data.models.CatImg
import retrofit2.http.GET

interface CatImgApi {
    @GET("images/search")
    suspend fun getRandomImg(): List<CatImg>
}
