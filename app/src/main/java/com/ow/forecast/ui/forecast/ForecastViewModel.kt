package com.ow.forecast.ui.forecast

import androidx.lifecycle.ViewModel
import com.ow.forecast.api.WeatherApi
import com.ow.forecast.models.WeatherForecast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers



class ForecastViewModel(val api: WeatherApi, val mView: ForecastView): ViewModel(){

    private val compositeDisposable = CompositeDisposable()

    fun getWeatherForecast() {
        compositeDisposable.add(api.getBelgradeWeatherForecast()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io()).subscribe({ response: WeatherForecast -> mView.didGetForecast(response) }) { throwable: Throwable ->
                mView.errorProcessingRequest(throwable.message!!)
                throwable.printStackTrace()
            })
    }




    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}