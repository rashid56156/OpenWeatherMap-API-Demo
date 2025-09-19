package com.ow.forecast.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ow.forecast.models.ForecastItem
import com.ow.forecast.repo.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(val repository: WeatherRepository) : ViewModel() {

    private val _weather = MutableLiveData<List<ForecastItem>?>()

    val weather: LiveData<List<ForecastItem>?>
        get() = _weather

    private val _isLoading = MutableLiveData<Boolean>()

    val isLoading : LiveData<Boolean>
        get() = _isLoading

    init {
        fetchWeatherForecast()
    }

    fun fetchWeatherForecast() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _weather.value = repository.getWeather().list
                _isLoading.value = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}