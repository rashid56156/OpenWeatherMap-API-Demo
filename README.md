
# 🌦️ WeatherApp (Kotlin + Hilt + Retrofit + MVVM)

A simple Android app that fetches weather data from a public API and displays it in a `RecyclerView`.
This project demonstrates **modern Android development best practices**:

* **MVVM architecture** (ViewModel + Repository pattern)
* **Hilt** for dependency injection
* **Retrofit** + **OkHttp** for networking
* **LiveData** for reactive UI updates
* **ViewBinding** for safe view access
* **RecyclerView** for list rendering

---

## 📱 Features

* Fetches weather data from a REST API
* Displays the data in a clean RecyclerView list
* Shows a **loading indicator** while fetching data
* Handles API responses gracefully
* Uses Hilt to manage dependencies

---

## 🏗️ Tech Stack

* **Kotlin** (100% codebase)
* **Hilt (Dagger)** for Dependency Injection
* **Retrofit2 + Gson** for REST API consumption
* **OkHttp Logging Interceptor** for network logs
* **AndroidX Lifecycle** (`ViewModel`, `LiveData`)
* **RecyclerView** for list rendering
* **ViewBinding** for type-safe UI references

---

## ⚙️ Setup & Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/weatherapp.git
   cd weatherapp
   ```

2. Open in **Android Studio**.

3. Add your base URL / API key (if required) inside `NetworkModule.kt`:

   ```kotlin
   .baseUrl("https://api.weatherapi.com/") // replace with real base URL
   ```

4. Sync Gradle and run the app.

---

## 🎯 Learning Goals

* How to structure an Android app using **MVVM + Repository**.
* Using **Hilt** to inject dependencies like Retrofit, Repositories, and ViewModels.
* Handling **UI state** with `LiveData` (`isLoading`, `weather`).
* Building a simple yet scalable codebase that can be extended with Paging, Room DB, or more API endpoints.

---

## 🚀 Future Improvements

* Implement **Paging 3** for large data sets
* Add **Unit Tests** with JUnit & MockK
* Add **Room Database** for offline caching

---

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
