package com.ow.forecast.di.component

import com.antumloyaltychampions.di.module.AppModule
import com.ow.forecast.di.module.NetworkModule
import com.ow.forecast.ui.forecast.ForecastFragment
import dagger.Component

import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(fragment: ForecastFragment)


}