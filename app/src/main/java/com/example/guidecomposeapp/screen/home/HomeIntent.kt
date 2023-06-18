package com.example.guidecomposeapp.screen.home

sealed interface HomeIntent {
    object ClickOnIncreaseButton : HomeIntent
    object ClickOnRestButton : HomeIntent
    object ClickOnTakeInputValueButton : HomeIntent
    object ClickShowAlertButton : HomeIntent
    object ClickSnackBarButton : HomeIntent
    object DismissAlertDialog : HomeIntent
    data class ClickToDetails(val data: Int) : HomeIntent
    data class UpdateUserInputData(val input: Int?) : HomeIntent

}