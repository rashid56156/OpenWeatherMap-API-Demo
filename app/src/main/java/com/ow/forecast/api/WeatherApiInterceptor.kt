package com.ow.forecast.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class WeatherApiInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("User-Agent", "ANDROID 1.0.0)")
            .build()

        return chain.proceed(request)
    }
}