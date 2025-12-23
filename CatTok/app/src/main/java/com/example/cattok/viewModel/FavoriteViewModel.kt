package com.example.cattok.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cattok.data.models.CatCard
import com.example.cattok.data.models.FavoriteCatEntity
import com.example.cattok.data.models.FavoriteVideoEntity
import com.example.cattok.data.models.PexelsVideo
import com.example.cattok.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.Companion.getInstance(application).favoriteDao()
    private val _favoriteCats = MutableLiveData<List<FavoriteCatEntity>>()
    val favoriteCats: LiveData<List<FavoriteCatEntity>> = _favoriteCats

    private val _favoriteVideos = MutableLiveData<List<FavoriteVideoEntity>>()
    val favoriteVideos: LiveData<List<FavoriteVideoEntity>> = _favoriteVideos


    fun loadFavorites() {
        viewModelScope.launch {
            val cats = withContext(Dispatchers.IO) { dao.getAllFacts() }
            val videos = withContext(Dispatchers.IO) { dao.getAllVideos() }
            _favoriteCats.value = cats
            _favoriteVideos.value = videos
        }
    }


    fun addFavoriteFact(card: CatCard) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertFact(
                FavoriteCatEntity(
                    imageUrl = card.imageUrl,
                    fact = card.fact
                )
            )
            loadFavorites()
        }
    }

    fun addFavoriteVideo(video: PexelsVideo) {
        val file = video.video_files.firstOrNull() ?: return
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertVideo(
                FavoriteVideoEntity(
                    previewUrl = video.image,
                    videoUrl = file.link
                )
            )
            loadFavorites()
        }
    }
}