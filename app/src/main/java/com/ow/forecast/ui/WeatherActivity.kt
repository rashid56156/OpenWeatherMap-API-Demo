package com.ow.forecast.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ow.forecast.R
import com.ow.forecast.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        supportFragmentManager.beginTransaction().replace(R.id.frameContainer, ForecastFragment())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}