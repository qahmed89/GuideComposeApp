package com.example.guidecomposeapp.screen.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guidecomposeapp.R
import com.example.guidecomposeapp.helper.UiText
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun DetailsScreen(
    state: DetailsUiState,
    onIntent: (DetailsIntent) -> Unit,
    navController: NavController,
    effect: SharedFlow<DetailsEffect>
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues),
            contentAlignment = Alignment.Center
        )
        {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = UiText.StringResource(R.string.the_data_is, state.data).asString(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,

                    )
            }
        }

    }
}