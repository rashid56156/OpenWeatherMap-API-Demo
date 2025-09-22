package com.ow.forecast.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ow.forecast.models.Weather
import com.ow.forecast.repo.WeatherRepository
import com.ow.forecast.api.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(val repo: WeatherRepository) : ViewModel() {

    private val _weatherResult = MutableLiveData<ApiResult<Weather>>()
    val weatherResult: LiveData<ApiResult<Weather>> = _weatherResult

    init {
        fetchWeatherForecast()
    }

    fun fetchWeatherForecast() {
        viewModelScope.launch {
            _weatherResult.value = ApiResult.Loading
            _weatherResult.value = repo.getWeather()
        }

    }
}