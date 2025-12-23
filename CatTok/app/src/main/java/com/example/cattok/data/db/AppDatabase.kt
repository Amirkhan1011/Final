package com.example.cattok.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cattok.data.models.FavoriteCatEntity
import com.example.cattok.data.models.FavoriteVideoEntity
import com.example.cattok.data.db.FavoriteDao


@Database(
    entities = [FavoriteCatEntity::class, FavoriteVideoEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "favorites.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
