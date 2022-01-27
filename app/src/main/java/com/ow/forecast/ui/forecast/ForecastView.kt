package com.ow.forecast.ui.forecast


import com.ow.forecast.models.WeatherForecast

interface ForecastView {

    fun didGetForecast(response: WeatherForecast)
    fun errorProcessingRequest(message: String)

}