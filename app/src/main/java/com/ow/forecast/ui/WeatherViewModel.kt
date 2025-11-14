package com.ow.forecast.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ow.forecast.models.Weather
import com.ow.forecast.repo.WeatherRepository
import com.ow.forecast.api.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(val repo: WeatherRepository) : ViewModel() {

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 1)

    // Expose immutable StateFlow
    @OptIn(ExperimentalCoroutinesApi::class)
    val weatherResult = refreshTrigger
        .flatMapLatest {
            repo.getWeather() // returns Flow<ApiResult<Weather>>
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ApiResult.Loading)


    fun fetchWeatherForecast() {
        viewModelScope.launch {
            refreshTrigger.emit(Unit)
        }
    }

}