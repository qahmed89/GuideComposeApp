package com.example.guidecomposeapp.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guidecomposeapp.helper.UiText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel() : ViewModel() {
    private val _state = MutableStateFlow(DetailsUiState())
    val state = _state.asStateFlow()
    private val _effect = MutableSharedFlow<DetailsEffect>()
    val effect = _effect.asSharedFlow()

    fun onIntent(intent: DetailsIntent) {
        viewModelScope.launch {
            when (intent) {

                is DetailsIntent.InitDetailsScreen -> initDetailsScreen(intent.data)
            }
        }
    }



    private fun initDetailsScreen(data: Int) {
        _state.update {
            it.copy(data = data)
        }
    }
}