package com.antumloyaltychampions.di.module


import android.app.Application
import dagger.Provides
import dagger.Module
import javax.inject.Singleton


@Module
class AppModule(application: Application) {
    private val mApplication: Application?

    @Provides
    @Singleton
    fun providesApplication(): Application? {
        return mApplication
    }

    init {
        mApplication = application
    }
}