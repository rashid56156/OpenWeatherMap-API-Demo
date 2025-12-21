package com.ow.forecast.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.ui.platform.LocalContext

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
                        ?: return@composable // safety

                    WeatherDetailScreen(
                        LocalContext.current,
                        itemId = itemId,
                        viewModel = viewModel
                    )

                }
            }
        }
    }
}

