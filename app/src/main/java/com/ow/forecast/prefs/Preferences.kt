package com.ow.forecast.prefs

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class Preferences @Inject constructor(private val pref: SharedPreferences) {
    suspend fun saveData(key: String, value: String) {
        pref.edit {
            putString(key, value)
        }
    }

    suspend fun getData(key: String): String? {
        return pref.getString(key, null)
    }
}