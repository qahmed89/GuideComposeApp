package com.example.guidecomposeapp.screen.details

sealed interface DetailsIntent {
    data class InitDetailsScreen(val data: Int) : DetailsIntent
}