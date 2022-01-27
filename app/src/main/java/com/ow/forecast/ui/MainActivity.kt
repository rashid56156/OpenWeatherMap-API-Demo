package com.ow.forecast.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ow.forecast.R
import com.ow.forecast.databinding.ActivityMainBinding
import com.ow.forecast.ui.forecast.ForecastFragment
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val fm = supportFragmentManager
        fm.beginTransaction().replace(R.id.frameContainer, ForecastFragment()).commit()
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null

    }

    fun showProgress() {
        try {
            binding!!.progress.visibility = View.VISIBLE
        } catch(e: Exception){
            e.printStackTrace()
        }
    }

    fun hideProgress() {
        try {
            binding!!.progress.visibility = View.INVISIBLE
        } catch(e: Exception){
            e.printStackTrace()
        }

    }
}