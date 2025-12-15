package com.ow.forecast.repo

import com.ow.forecast.api.ApiResult
import com.ow.forecast.api.ApiService
import com.ow.forecast.models.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: ApiService) : WeatherRepository {

    // function to fetch weather data from the API
    override fun getWeather(): Flow<ApiResult<Weather>> = flow {
        // Step 1: Emit Loading before making the network call
        emit(ApiResult.Loading)

        try {
            val response = api.getWeatherForecast()

            if (response.cod.equals("200", ignoreCase = true)) {
                // Step 2: Emit Success state
                emit(ApiResult.Success(response))
            } else {
                // Step 3: Emit Error state with API error
                emit(ApiResult.Error("API Error", response.cod?.toIntOrNull()))
            }

        } catch (e: Exception) {
            // Step 4: Emit Error state for exceptions
            emit(ApiResult.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }

}