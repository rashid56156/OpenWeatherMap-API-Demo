# WeatherForecast
Demo Android Application for OpenWeatherMap API 

## Table of Contents

- [App](#app)
- [Api](#api)
- [Architecture](#architecture)
- [Libraries](#libraries)

## App
A demo application showing weather forecast of Belgrade, Serbia.
The project has been written in Kotlin language. For network requests, it uses Retrofit with RxJava.
Dagger2 has been used for Dependency injection.

## Api
App uses OpenWeatherMap API to fetch 5 day forecast for any location or city. 
It includes weather forecast data with 3-hour step.

## Architecture
The project is built using MVVM pattern. MVVM allows for the separation of concern which also makes testing easier.


## Libraries

Libraries used in the whole application are:

- [RxJava](https://github.com/ReactiveX/RxJava) - RxJava is a Java VM implementation of Reactive Extensions: a library for composing asynchronous and event-based programs by using observable sequences.
- [Dagger2](https://dagger.dev/dev-guide/) - Used for Dependency injection
- [Retrofit](https://square.github.io/retrofit/) - Turns your HTTP API into a Java interface.
- [Glide](https://github.com/bumptech/glide) - Media management and image loading framework for Android

