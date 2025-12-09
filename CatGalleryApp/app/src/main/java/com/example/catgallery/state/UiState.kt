package com.example.catgallery.state

import com.example.catgallery.data.models.CatCard

sealed class UiState {
    object Loading : UiState()
    data class Success(val cats: List<CatCard>) : UiState()
    data class Error(val message: String) : UiState()
}
