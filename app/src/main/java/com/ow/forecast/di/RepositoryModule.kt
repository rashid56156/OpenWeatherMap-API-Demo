package com.ow.forecast.di

import com.ow.forecast.repo.DefaultWeatherRepository
import com.ow.forecast.repo.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindWeatherRepository(
        impl: DefaultWeatherRepository
    ): WeatherRepository
}