package com.ow.forecast.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ow.forecast.api.ApiResult
import com.ow.forecast.models.ForecastUiModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import com.ow.forecast.R

@AndroidEntryPoint
class WeatherActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: WeatherViewModel = hiltViewModel()
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = ScreenRoutes.WeatherList.route
            ) {

                composable(ScreenRoutes.WeatherList.route) {
                    WeatherScreen(
                        viewModel = viewModel,
                        onClick = { item ->
                            navController.navigate(
                                ScreenRoutes.WeatherDetails.createRoute(item.id)
                            )
                        }
                    )
                }

                composable(
                    route = ScreenRoutes.WeatherDetails.route,
                    arguments = listOf(
                        navArgument("itemId") {
                            type = NavType.IntType
                        }
                    )
                ) { backStackEntry ->

                    val itemId = backStackEntry.arguments?.getInt("itemId")
                        ?: return@composable   // safety

                    WeatherDetailScreen(
                        applicationContext,
                        itemId = itemId,
                        viewModel = viewModel)

                }
            }
        }
    }

    @Composable
    fun WeatherScreen(
        viewModel: WeatherViewModel, onClick: (ForecastUiModel) -> Unit
    ) {
        // lifecycle-aware collection to a Compose State
        val result by viewModel.weatherResult.collectAsStateWithLifecycle()

        when (result) {
            is ApiResult.Loading -> {
                // render loading UI
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is ApiResult.Success -> {
                val list = viewModel.forecastCache.collectAsState().value
                WeatherList(applicationContext, list, onClick = onClick)
            }
            is ApiResult.Error -> {
                // optionally show error UI; toast already shown via LaunchedEffect
                Box(
                    modifier = Modifier.fillMaxSize().padding(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = getString(R.string.fetching_contributors_error))
                }
            }
        }
    }
}

