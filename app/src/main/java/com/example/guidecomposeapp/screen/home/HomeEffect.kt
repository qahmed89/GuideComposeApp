package com.example.guidecomposeapp.screen.home

import com.example.guidecomposeapp.R
import com.example.guidecomposeapp.helper.UiText

sealed class HomeEffect() {
    data class NavToDetailsScreen (val data: Int) : HomeEffect()
    data class ShowSnackBar(val message: UiText) : HomeEffect()

}

data class AlertDialogModel(
    val title: UiText = UiText.StringResource(R.string.alert_title),
    val bodyMessage: UiText = UiText.StringResource(R.string.body_dialog),
    val cancelTextButton: UiText =  UiText.StringResource(R.string.cancel_button_dialog),
    val doneTextButton: UiText = UiText.StringResource(R.string.done_button_dialog)
)