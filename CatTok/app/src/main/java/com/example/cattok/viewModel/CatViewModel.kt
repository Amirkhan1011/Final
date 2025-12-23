package com.example.cattok.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cattok.api.CatFactRetrofit
import com.example.cattok.api.CatImgRetrofit
import com.example.cattok.data.models.CatCard
import com.example.cattok.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

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
                _state.value = UiState.Error(mapNetworkError(e))
            }
        }
    }

    private fun mapNetworkError(e: Exception): String =
        when (e) {
            is UnknownHostException -> "No internet connection"
            is SocketTimeoutException -> "Connection timeout"
            is IOException -> "Network error"
            else -> e.message ?: "Unknown error"
        }
}
