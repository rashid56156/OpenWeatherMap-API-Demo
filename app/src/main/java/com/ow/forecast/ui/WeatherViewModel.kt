package com.ow.forecast.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ow.forecast.repo.WeatherRepository
import com.ow.forecast.api.ApiResponse
import com.ow.forecast.models.ForecastUiModel
import com.ow.forecast.models.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(val repo: WeatherRepository) : ViewModel() {

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 1)

    // 🔑 Cache (source of truth for details screen)
    private val _forecastCache = MutableStateFlow<List<ForecastUiModel>>(emptyList())

    val forecastCache: StateFlow<List<ForecastUiModel>> = _forecastCache.asStateFlow()

    init {
        fetchWeatherForecast()
    }

    // Expose immutable StateFlow
    @OptIn(ExperimentalCoroutinesApi::class)
    val weatherResult: StateFlow<ApiResponse<Weather>> =
        refreshTrigger
            .flatMapLatest {
                repo.getWeather()
            }
            .onEach { result ->
                if (result is ApiResponse.Success) {
                    _forecastCache.value = result.data.list.orEmpty().mapNotNull { item ->
                        val id = item.dt ?: return@mapNotNull null
                        ForecastUiModel(
                            id = id,
                            dateText = item.dtTxt.orEmpty(),
                            temp = item.main?.temp,
                            pressure = item.main?.pressure,
                            humidity = item.main?.humidity,
                            weatherId = item.weather?.firstOrNull()?.id
                        )

                    }
                }
            }
            .stateIn(
                initialValue = ApiResponse.Loading,
                scope = viewModelScope,
                started = SharingStarted.Eagerly
            )


    fun fetchWeatherForecast() {
        viewModelScope.launch {
            refreshTrigger.emit(Unit)
        }
    }

    // 🔍 Used by details screen
    fun getForecastById(id: Int): ForecastUiModel? {
        val list = _forecastCache.value
        return list.firstOrNull { it.id == id }
    }

}