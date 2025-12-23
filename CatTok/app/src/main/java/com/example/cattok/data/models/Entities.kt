package com.example.cattok.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_facts")
data class FavoriteCatEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageUrl: String,
    val fact: String
)

@Entity(tableName = "favorite_videos")
data class FavoriteVideoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val previewUrl: String,
    val videoUrl: String
)
