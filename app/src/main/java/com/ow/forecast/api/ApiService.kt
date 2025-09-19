package com.ow.forecast.api


import com.ow.forecast.models.Weather
import com.ow.forecast.utilities.Constants
import retrofit2.http.*


interface ApiService {
    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("id") cityId: String = Constants.LOCATION_ID,
        @Query("appid") apiKey: String = Constants.API_KEY,
        @Query("units") unit: String = "metric"
    ): Weather

}