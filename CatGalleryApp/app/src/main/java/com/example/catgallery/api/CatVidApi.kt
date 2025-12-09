package com.example.catgallery.api


import com.example.catgallery.data.models.CatVid
import retrofit2.http.GET
import retrofit2.http.Query

interface CatVidApi {

    @GET("videos/search")
    suspend fun getCatVideos(
        @Query("query") query: String = "cats",
        @Query("per_page") perPage: Int = 5,
        @Query("page") page: Int = 1,
        @Query("orientation") orientation: String = "portrait"
    ): CatVid
}
