package com.example.guidecomposeapp.screen.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val data: Int = 0,
    val inputValue : Int = 0 ,
    val showAlertDialog : Boolean = false,
    val alertDialogModel : AlertDialogModel ?= null
)
