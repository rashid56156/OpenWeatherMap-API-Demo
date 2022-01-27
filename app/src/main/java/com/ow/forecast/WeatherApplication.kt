package com.ow.forecast

import android.app.Application
import com.ow.forecast.di.component.AppComponent
import com.antumloyaltychampions.di.module.AppModule
import com.ow.forecast.di.component.DaggerAppComponent
import com.ow.forecast.di.module.NetworkModule

class WeatherApplication : Application() {

    init{
        instance_ = this
        component = createDaggerComponent()

    }

    override fun onCreate() {
        super.onCreate()

    }

    companion object {
            private var component: AppComponent? = null
            private lateinit var instance_: WeatherApplication

            fun getInstance() = instance_


            fun getComponent(): AppComponent {
                return (if (component == null) createDaggerComponent() else component!!)
            }


            fun clearComponent() {
                component = null
            }

            fun createDaggerComponent(): AppComponent {
                return DaggerAppComponent.builder().appModule(AppModule(getInstance())).networkModule(
                    NetworkModule()
                ).build()
            }



    }
}