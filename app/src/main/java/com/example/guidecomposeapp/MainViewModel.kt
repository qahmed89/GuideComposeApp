package com.example.guidecomposeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _mainStat = MutableStateFlow(MainState())
    val mainState = _mainStat.asStateFlow()

    private val _mainEffect = MutableSharedFlow<MainEffect>()
    val mainEffect = _mainEffect.asSharedFlow()

   
    fun onIntent(intent: MainIntent) {
        viewModelScope.launch {
            when (intent) {
                is MainIntent.ChangeAppBarForDetailsScreen -> changeStateForAppBarDetails(intent)
                is MainIntent.ChangeAppBarForHomeScreen -> changeStateForAppBarHome(intent)
                MainIntent.ClickOnCloseJourney -> closeJourney()
                MainIntent.ClickOnNavigationIcon -> popToPreviousScreen()
            }
        }
    }


    private fun changeStateForAppBarHome(intent: MainIntent.ChangeAppBarForHomeScreen) {
        _mainStat.update {
            it.copy(appBarStat = intent.appBarStat)
        }
    }

    private fun changeStateForAppBarDetails(intent: MainIntent.ChangeAppBarForDetailsScreen) {
        _mainStat.update {
            it.copy(appBarStat = intent.appBarStat)
        }
    }

    private suspend fun popToPreviousScreen() {
        _mainEffect.emit(
            MainEffect.NavToPreviousScreen
        )
    }

    private suspend fun closeJourney() {
        _mainEffect.emit(
            MainEffect.CloseJourney
        )
    }
}