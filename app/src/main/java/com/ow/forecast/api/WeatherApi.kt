package com.ow.forecast.api


import com.ow.forecast.models.WeatherForecast
import com.ow.forecast.utilities.Constants
import io.reactivex.Flowable
import retrofit2.http.*


interface WeatherApi {
    @GET("forecast")
    fun getBelgradeWeatherForecast(
        @Query("id") cityId: String = Constants.BELGRADE_ID,
        @Query("appid") apiKey: String = Constants.API_KEY,
        @Query("units") unit: String = "metric"
    ): Flowable<WeatherForecast>

}