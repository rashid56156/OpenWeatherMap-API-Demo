package com.ow.forecast.ui

sealed class ScreenRoutes(val route: String) {
    object WeatherList : ScreenRoutes("weather_list")
    object WeatherDetails : ScreenRoutes("weather_details/{itemId}") {
        fun createRoute(itemId: Int?) = "weather_details/$itemId"
    }
}