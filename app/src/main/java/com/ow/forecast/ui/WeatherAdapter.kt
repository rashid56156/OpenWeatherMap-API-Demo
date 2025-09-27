package com.ow.forecast.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ow.forecast.R
import com.ow.forecast.databinding.ItemWeatherBinding
import com.ow.forecast.models.ForecastItem
import com.ow.forecast.utilities.Constants

class WeatherAdapter(private val mForecasts: MutableList<ForecastItem>?) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
            val mForecastItem = mForecasts?.get(position)

            holder.binding.apply {
                tvTemp.text =
                    root.context.getString(R.string.temp, mForecastItem?.main!!.temp)
                tvTime.text = mForecastItem.dtTxt
                tvWeatherDescription.text = root.context.getString(
                    R.string.current_weather_status,
                    mForecastItem.wind!!.speed.toString(),
                    mForecastItem.main.pressure,
                    mForecastItem.main.humidity,
                )

                Glide.with(root.context)
                    .load(
                        String.format(
                            Constants.WEATHER_ICON_URL,
                            mForecastItem.weather!![0]!!.icon
                        )
                    )
                    .centerCrop()
                    .into(ivWeatherIcon)
        }
    }

    override fun getItemCount(): Int {
        return mForecasts?.size ?: 0
    }
}