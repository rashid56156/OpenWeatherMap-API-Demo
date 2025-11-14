package com.ow.forecast.api

import com.ow.forecast.models.Weather

sealed class ApiResult<out T> {
    data class Success<out T>(val data: Weather) : ApiResult<T>()
    data class Error(val message: String, val code: Int? = null) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}