package com.example.guidecomposeapp.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guidecomposeapp.R
import com.example.guidecomposeapp.helper.UiText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeEffect>()
    val effect = _effect.asSharedFlow()

    fun onIntent(intent: HomeIntent) {
        viewModelScope.launch {
            when (intent) {
                HomeIntent.ClickOnIncreaseButton -> increaseData()
                HomeIntent.ClickOnRestButton -> restData()
                is HomeIntent.UpdateUserInputData -> updateUserInputValue(intent.input)
                HomeIntent.ClickOnTakeInputValueButton -> updateDataValue(_state.value.inputValue)
                HomeIntent.ClickShowAlertButton -> showAlertDialog()
                HomeIntent.DismissAlertDialog -> dismissAlertDialog()
                HomeIntent.ClickSnackBarButton -> showSnackBar()
                is HomeIntent.ClickToDetails ->  navToHomeScreen(intent.data)
            }
        }
    }

    private fun increaseData() {
        _state.update {
            it.copy(data = it.data.plus(1))
        }
    }

    private fun restData() {
        _state.update {
            it.copy(data = 0)
        }
    }

    private fun updateUserInputValue(input: Int?) {
        _state.update {
            it.copy(inputValue = input ?: 0)
        }
    }

    private fun updateDataValue(data: Int?) {
        _state.update {
            it.copy(data = data ?: 0)
        }
    }

    private fun showAlertDialog() {
        _state.update {
            it.copy(showAlertDialog = true, alertDialogModel = AlertDialogModel())
        }
    }

    private fun dismissAlertDialog() {
        _state.update {
            it.copy(showAlertDialog = false, alertDialogModel = null)
        }
    }

    private suspend fun showSnackBar(message: UiText = UiText.StringResource(R.string.messgae_snack_bar)) {
        _effect.emit(HomeEffect.ShowSnackBar(message))
    }

    private suspend fun navToHomeScreen(data: Int) {
        _effect.emit(
            HomeEffect.NavToDetailsScreen(data)
        )
    }

}