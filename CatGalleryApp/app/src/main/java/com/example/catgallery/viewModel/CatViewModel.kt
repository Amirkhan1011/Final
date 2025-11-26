package com.example.catgallery.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catgallery.api.CatFactRetrofit
import com.example.catgallery.api.CatImgRetrofit
import com.example.catgallery.models.CatCard
import com.example.catgallery.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatViewModel : ViewModel() {

    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState> = _state

    fun loadCats(count: Int) {
        _state.value = UiState.Loading

        viewModelScope.launch {
            try {
                val jobs = (1..count).map {
                    async(Dispatchers.IO) {
                        val imgList = CatImgRetrofit.api.getRandomImg()
                        val img = imgList.firstOrNull()
                        val fact = CatFactRetrofit.api.getRandomFact()
                        if (img != null) {
                            CatCard(imageUrl = img.url, fact = fact.fact)
                        } else {
                            null
                        }
                    }
                }

                val cards = jobs.awaitAll().filterNotNull()

                _state.value = UiState.Success(cards)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
