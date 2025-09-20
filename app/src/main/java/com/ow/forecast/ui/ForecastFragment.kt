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
import com.ow.forecast.api.ApiResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment : Fragment() {

    private lateinit var binding: FragmentForecastBinding
    private val viewModel: ForecastViewModel by viewModels()
    private var mForecasts: MutableList<ForecastItem>? = mutableListOf()
    private lateinit var mAdapter: ForecastAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)

        setupUI()
        setupObservers()

        return binding.root
    }


    private fun setupUI() {
        mAdapter = ForecastAdapter(mForecasts)
        binding.rvForecast.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.rvForecast.adapter = mAdapter
    }

    private fun setupObservers() {
        viewModel.apply {
            weatherResult.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ApiResult.Success -> {
                        hideLoading()
                        updateForecastList(result.data.list)
                    }

                    is ApiResult.Error -> {
                        hideLoading()
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }

                    is ApiResult.Loading -> {
                        showLoading()
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
