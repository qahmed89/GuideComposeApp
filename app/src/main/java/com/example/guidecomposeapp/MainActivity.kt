package com.example.guidecomposeapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guidecomposeapp.helper.toParcelable
import com.example.guidecomposeapp.screen.details.DetailsIntent
import com.example.guidecomposeapp.screen.details.DetailsScreen
import com.example.guidecomposeapp.screen.details.DetailsViewModel
import com.example.guidecomposeapp.screen.home.Details
import com.example.guidecomposeapp.screen.home.HomeScreen
import com.example.guidecomposeapp.screen.home.HomeViewModel
import com.example.guidecomposeapp.screen.home.composable.AppBarWithNavigationAndExitIcon
import com.example.guidecomposeapp.ui.theme.GuideComposeAppTheme
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel: MainViewModel by viewModels()
        setContent {
            val mainState by mainViewModel.mainState.collectAsStateWithLifecycle()
            val mainEffect = mainViewModel.mainEffect
            val navController = rememberNavController()

            LaunchedEffect(key1 = Unit) {
                mainEffect.collectLatest {
                    when (it) {
                        MainEffect.CloseJourney -> finish()
                        MainEffect.NavToPreviousScreen -> navController.popBackStack()
                    }
                }
            }
            GuideComposeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AppBarWithNavigationAndExitIcon(
                            state = mainState,
                            onIntent = {
                                mainViewModel.onIntent(it)
                            })
                    }
                ) { paddingValue ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValue),
                        color = MaterialTheme.colors.background,
                    ) {

                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") {
                                val viewModel by viewModels<HomeViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()
                                mainViewModel.onIntent(MainIntent.ChangeAppBarForHomeScreen())
                                HomeScreen(
                                    onIntent = { viewModel.onIntent(it) },
                                    state = state,
                                    effect = viewModel.effect,
                                    navController = navController
                                )
                            }

                            composable(
                                route = "detials/{data}",
                            ) {
                                val args: Details =
                                    it.arguments?.toParcelable<Details>("data") ?: Details(0)
                                val viewModel by viewModels<DetailsViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()
                                mainViewModel.onIntent(MainIntent.ChangeAppBarForDetailsScreen())
                                viewModel.onIntent(DetailsIntent.InitDetailsScreen(args.data))
                                DetailsScreen(
                                    onIntent = { intent -> viewModel.onIntent(intent) },
                                    state = state,
                                    effect = viewModel.effect,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



