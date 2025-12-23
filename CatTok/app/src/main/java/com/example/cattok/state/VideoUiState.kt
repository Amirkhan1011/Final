package com.example.cattok.state

import com.example.cattok.data.models.PexelsVideo

sealed class VideoUiState {
    object Loading : VideoUiState()
    data class Success(val videos: List<PexelsVideo>) : VideoUiState()
    data class Error(val message: String) : VideoUiState()
}
