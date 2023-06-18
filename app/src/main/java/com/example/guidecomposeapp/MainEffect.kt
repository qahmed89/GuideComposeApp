package com.example.guidecomposeapp

sealed interface MainEffect {
    object CloseJourney : MainEffect
    object NavToPreviousScreen : MainEffect
}