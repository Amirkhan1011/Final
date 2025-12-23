package com.example.cattok.state

import com.example.cattok.data.models.CatCard

sealed class UiState {
    object Loading : UiState()
    data class Success(val cats: List<CatCard>) : UiState()
    data class Error(val message: String) : UiState()
}
