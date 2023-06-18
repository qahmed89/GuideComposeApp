package com.example.guidecomposeapp

import com.example.guidecomposeapp.helper.UiText
import com.example.guidecomposeapp.screen.details.DetailsIntent

sealed interface MainIntent {
    data class ChangeAppBarForHomeScreen(
        val appBarStat: AppBarStat = AppBarStat(showNavigationIcon = false, showExitIcon = true , title = UiText.StringResource(R.string.home_title_screen))
    ) : MainIntent

    data class ChangeAppBarForDetailsScreen(
        val appBarStat: AppBarStat = AppBarStat(showNavigationIcon = true, showExitIcon = true , title = UiText.StringResource(R.string.details_title_screen))
    ) : MainIntent

    object ClickOnNavigationIcon : MainIntent
    object ClickOnCloseJourney : MainIntent
}