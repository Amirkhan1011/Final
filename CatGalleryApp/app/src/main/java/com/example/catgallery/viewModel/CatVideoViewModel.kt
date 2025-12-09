package com.example.catgallery.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catgallery.api.CatVidRetrofit
import com.example.catgallery.state.VideoUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatVideoViewModel : ViewModel() {
    private val _state = MutableLiveData<VideoUiState>()
    val state: LiveData<VideoUiState> = _state
    private var currentPage = 1
    private val allVideos = mutableListOf<com.example.catgallery.data.models.PexelsVideo>()
    private var isLoading = false


    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            if (allVideos.isEmpty()) {
                _state.value = VideoUiState.Loading
            }

            try {
                val response = withContext(Dispatchers.IO) {
                    CatVidRetrofit.api.getCatVideos(page = currentPage)
                }
                currentPage++
                allVideos.addAll(response.videos)
                _state.value = VideoUiState.Success(allVideos.toList())
            } catch (e: Exception) {
                _state.value = VideoUiState.Error(
                    e.message ?: "Error loading cat videos"
                )
            } finally {
                isLoading = false
            }
        }
    }
}
