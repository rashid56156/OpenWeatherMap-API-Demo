package com.ow.forecast.models

data class ForecastUiModel(
    val id: Int,           // dt
    val dateText: String,
    val temp: Double?,
    val pressure: Int?,
    val humidity: Int?,
    val weatherId: Int?
)