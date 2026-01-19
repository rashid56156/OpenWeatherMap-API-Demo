package com.ow.forecast.repo

import com.ow.forecast.models.Weather
import com.ow.forecast.api.ApiResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(): Flow<ApiResponse<Weather>>
}