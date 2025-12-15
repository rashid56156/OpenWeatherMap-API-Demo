# 🌦️ WeatherApp
### (Kotlin · Jetpack Compose · Compose Navigation · Hilt · Retrofit · MVVM)

A modern Android weather application built using Jetpack Compose and a single-activity architecture.  
This project demonstrates current best practices in Android development, focusing on declarative UI, state-driven architecture, and clean separation of concerns.

---

## ✨ Highlights

- 100% Jetpack Compose UI (no XML, no RecyclerView, no Fragments)
- Single-Activity architecture
- Jetpack Compose Navigation for screen-to-screen navigation
- MVVM + Repository pattern
- Hilt for dependency injection
- Retrofit + OkHttp for networking
- StateFlow / Flow-based UI state
- Clean handling of list → detail navigation
- Scalable, testable, and interview-ready codebase

---

## 📱 Features

- Fetches multi-hour / multi-day weather forecast from a public API
- Displays forecast data using Compose LazyColumn
- Shows:
   - Date & time
   - Temperature (°C)
   - Pressure (hPa)
   - Humidity (%)
- Details screen for individual forecast items
- Loading and error states
- Lifecycle-aware state collection
- Efficient recomposition and caching

---

## 🧠 Architecture Overview

WeatherActivity (Single Activity)  
└── NavHost (Compose Navigation)  
&nbsp;&nbsp;&nbsp;&nbsp;├── WeatherListScreen  
&nbsp;&nbsp;&nbsp;&nbsp;└── WeatherDetailScreen

### Architecture Pattern

- MVVM
   - ViewModel → business logic and state
   - Repository → data source abstraction
   - Composable UI → stateless, reactive rendering

### Navigation Strategy

- Uses Jetpack Compose Navigation
- Navigates using stable IDs (forecast timestamp `dt`)
- Avoids passing large objects through routes
- Shared ViewModel scoped to the navigation graph

---

## 🏗️ Tech Stack

### Language & UI
- Kotlin
- Jetpack Compose
- Material 3

### Architecture & State
- MVVM
- StateFlow / Flow
- Lifecycle-aware state collection

### Dependency Injection
- Hilt (Dagger)

### Networking
- Retrofit
- OkHttp
- Gson
- OkHttp Logging Interceptor

### Navigation
- Jetpack Compose Navigation

---

## ⚙️ Setup & Installation

1. Clone the repository:

   git clone https://github.com/your-username/weatherapp.git  
   cd weatherapp

2. Open the project in Android Studio (Giraffe or newer recommended).

3. Configure API details in `NetworkModule.kt`:

   .baseUrl("https://api.openweathermap.org/")

4. Add your API key if required.

5. Sync Gradle and run the app.

---

## 🎯 Learning Goals

This project is intentionally designed to demonstrate:

- How to build a Compose-first Android app
- How Compose Navigation works internally
- How to manage UI state with Flow
- How to share a ViewModel across multiple screens
- How to avoid common Compose pitfalls:
   - Recomposition bugs
   - Incorrect ViewModel scoping
   - Navigation argument mismatches
- How to structure code for scalability and testability

---

## 🚀 Future Improvements

- Add Pull-to-Refresh
- Add navigation animations
- Add Room database for offline caching
- Handle process death recovery
- Add unit tests for ViewModel and repository
- Add UI tests for Compose screens
- Support dark mode and theming
- Add deep links to detail screens

---

## 📜 License

This project is licensed under the MIT License.  
See the LICENSE file for details.

---

### Final Note

This codebase reflects modern Android development (2024+).  
It intentionally avoids legacy patterns (XML, Fragments, RecyclerView) in favor of declarative UI, unidirectional data flow, and Jetpack Compose best practices.