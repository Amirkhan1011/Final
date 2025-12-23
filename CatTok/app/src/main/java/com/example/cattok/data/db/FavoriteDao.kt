package com.example.cattok.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.cattok.data.models.FavoriteCatEntity
import com.example.cattok.data.models.FavoriteVideoEntity

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insertFact(cat: FavoriteCatEntity)

    @Query("SELECT * FROM favorite_facts")
    suspend fun getAllFacts(): List<FavoriteCatEntity>

    @Delete
    suspend fun deleteFact(cat: FavoriteCatEntity)

    @Insert
    suspend fun insertVideo(video: FavoriteVideoEntity)

    @Query("SELECT * FROM favorite_videos")
    suspend fun getAllVideos(): List<FavoriteVideoEntity>

    @Delete
    suspend fun deleteVideo(video: FavoriteVideoEntity)
}
