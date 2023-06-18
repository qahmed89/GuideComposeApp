package com.example.guidecomposeapp

import com.example.guidecomposeapp.helper.UiText

data class MainState(
    val  appBarStat: AppBarStat = AppBarStat()
)

data class AppBarStat(
    val showNavigationIcon: Boolean = false,
    val showExitIcon: Boolean = false,
    val title : UiText = UiText.DynamicString("")
)
