package com.ow.forecast.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ow.forecast.databinding.FragmentForecastBinding
import com.ow.forecast.models.ForecastItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ow.forecast.api.ApiResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentForecastBinding
    private val viewModel: WeatherViewModel by viewModels()
    private var mForecasts: MutableList<ForecastItem>? = mutableListOf()
    private lateinit var mAdapter: WeatherAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, parent, false)

        setupUI()
        collectWeatherFlow()
        viewModel.fetchWeatherForecast()

        return binding.root
    }


    private fun setupUI() {
        mAdapter = WeatherAdapter(mForecasts)
        binding.rvForecast.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.rvForecast.adapter = mAdapter
    }

    private fun collectWeatherFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weatherResult.collectLatest { result ->
                    when (result) {
                        is ApiResult.Success -> {
                            hideLoading()
                            updateForecastList(result.data.list)
                        }
                        is ApiResult.Error -> {
                            hideLoading()
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                        }
                        is ApiResult.Loading -> showLoading()
                    }
                }
            }
        }
    }


    private fun updateForecastList(items: MutableList<ForecastItem>?) {
        mForecasts?.clear()
        mForecasts?.addAll(items?.toList() ?: emptyList())
        mAdapter.notifyDataSetChanged()
    }

    private fun showLoading() {
        binding.pbLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.pbLoading.visibility = View.INVISIBLE
    }

}
