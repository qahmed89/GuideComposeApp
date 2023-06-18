package com.example.guidecomposeapp.screen.home

import AlertCustomDialog
import android.app.Activity
import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.nativeKeyCode
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guidecomposeapp.R
import com.example.guidecomposeapp.helper.UiText
import com.example.guidecomposeapp.helper.asBundle
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.parcelize.Parcelize

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeUiState,
    onIntent: (HomeIntent) -> Unit,
    effect: SharedFlow<HomeEffect>,
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current as Activity


    LaunchedEffect(key1 = Unit) {
        effect.collectLatest {
            when (it) {
                is HomeEffect.ShowSnackBar -> scaffoldState
                    .snackbarHostState
                    .showSnackbar(it.message.asString(context = context))
                is HomeEffect.NavToDetailsScreen -> {
                    navController.navigate("detials/${Details(it.data).asBundle()}")
                }
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            AlertCustomDialog(
                showShow = state.showAlertDialog,
                alertDialogModel = state.alertDialogModel
            ) {
                onIntent(HomeIntent.DismissAlertDialog)
            }

            Column(
                modifier = modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TextField(
                    value = state.inputValue.toString(),
                    modifier = Modifier.onKeyEvent { key ->
                        if (key.key.nativeKeyCode == android.view.KeyEvent.KEYCODE_DEL
                            && state.inputValue.toString().length == 1
                        ) {
                            onIntent(HomeIntent.UpdateUserInputData(0))
                        }
                        false
                    },
                    onValueChange = {
                        val value = kotlin.runCatching { it.toInt() }.getOrElse { 0 }
                        onIntent(HomeIntent.UpdateUserInputData(value))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(stringResource(id = R.string.enter_the_data)) },
                    singleLine = true,

                    )


                Text(
                    text = UiText.StringResource(R.string.the_data_is, state.data).asString(),
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    fontSize = 19.sp,
                )
                Button(onClick = { onIntent(HomeIntent.ClickOnIncreaseButton) }) {
                    Text(text = stringResource(id = R.string.increase))
                }

                Button(onClick = { onIntent(HomeIntent.ClickOnRestButton) }) {
                    Text(text = stringResource(id = R.string.reset))
                }

                Button(onClick = { onIntent(HomeIntent.ClickOnTakeInputValueButton) }) {
                    Text(text = stringResource(id = R.string.input_data))
                }

                Button(onClick = { onIntent(HomeIntent.ClickShowAlertButton) }) {
                    Text(text = stringResource(id = R.string.show_dialog))
                }

                Button(onClick = { onIntent(HomeIntent.ClickSnackBarButton) }) {
                    Text(text = stringResource(id = R.string.show_snackBar))
                }

                Button(onClick = { onIntent(HomeIntent.ClickToDetails(state.data)) }) {
                    Text(text = stringResource(id = R.string.to_details))
                }
            }

        }

    }
}

@Parcelize
data class Details(
    val data: Int
) : Parcelable

