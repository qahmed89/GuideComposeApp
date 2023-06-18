package com.example.guidecomposeapp.screen.home.composable

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.guidecomposeapp.MainIntent
import com.example.guidecomposeapp.MainState

@Composable
fun AppBarWithNavigationAndExitIcon(
    text: String = "",
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    state: MainState,
    onIntent: (MainIntent) -> Unit
) {
    val navigationIcon: @Composable (() -> Unit) = {
        IconButton(onClick = { onIntent(MainIntent.ClickOnNavigationIcon) }) {
            Icon(startIcon ?: Icons.Filled.ArrowBack, null)
        }
    }

    val action :@Composable RowScope.() -> Unit= {
        if (state.appBarStat.showExitIcon) {
            IconButton(onClick = { onIntent(MainIntent.ClickOnCloseJourney) }) {
                Icon(endIcon ?: Icons.Filled.Close, null)
            }
        }
    }


        TopAppBar(
            title = { Text(state.appBarStat.title.asString()) },
            navigationIcon = if (state.appBarStat.showNavigationIcon) {
                navigationIcon
            } else null,
            actions =  action

        )
    }