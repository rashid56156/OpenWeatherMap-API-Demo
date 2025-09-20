package com.ow.forecast.repo

import com.ow.forecast.api.ApiService
import com.ow.forecast.models.Weather
import com.ow.forecast.api.ApiResult
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: ApiService) {

    // Suspended function to fetch weather data from the API
    suspend fun getWeather(): ApiResult<Weather> {
        return try {
            // Make a network call to get the weather forecast
            val response = api.getWeatherForecast()
            // Check if the response code indicates success
            if (response.cod.equals("200", ignoreCase = true)) {
                // Return a successful result with the weather data
                ApiResult.Success(response)
            } else {
                // Return an error result with the API error code
                ApiResult.Error("API Error", response.cod?.toIntOrNull())
            }
        } catch (e: Exception) {
            // Handle exceptions and return an error result with the exception message
            ApiResult.Error(e.localizedMessage ?: "Unknown Error")
        }
    }

}