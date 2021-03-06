package com.ow.forecast.ui.forecast

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ow.forecast.R
import com.ow.forecast.databinding.ItemWeatherBinding
import com.ow.forecast.models.ForecastItem
import com.ow.forecast.utilities.Constants
import com.ow.forecast.utilities.DateTimeConversion

class ForecastAdapter(private val mForecasts: List<ForecastItem?>, private val mContext: Context) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val mForecastItem = mForecasts[position]

            binding.tvTemp.text = binding.root.context.getString(R.string.temp, mForecastItem?.main!!.temp)
            binding.tvTime.text = DateTimeConversion.changeDateFormat(mForecastItem.dtTxt!!)
            binding.tvWeatherDescription.text = binding.root.context.getString(
                R.string.current_weather_status,
                mForecastItem.wind!!.speed.toString(),
                mForecastItem.main.pressure,
                mForecastItem.main.humidity,
            )
            Glide.with(binding.root.context)
                .load(String.format(Constants.WEATHER_ICON_URL, mForecastItem.weather!![0]!!.icon))
                .centerCrop()
                .into(binding.ivWeatherIcon)

            binding.ivWeatherIcon.contentDescription = binding.root.context
                .getString(R.string.weather_image_description, mForecastItem.weather[0]!!.description)
        }
    }

    override fun getItemCount(): Int {
        return mForecasts.size
    }
}