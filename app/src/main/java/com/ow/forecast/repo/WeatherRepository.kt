package com.ow.forecast.repo

import com.ow.forecast.api.ApiService
import com.ow.forecast.models.Weather
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getWeather(): Weather {
        return apiService.getWeatherForecast()
    }
}