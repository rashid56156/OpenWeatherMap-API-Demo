package com.ow.forecast.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ow.forecast.databinding.FragmentForecastBinding
import com.ow.forecast.models.ForecastItem
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment : Fragment() {

    private lateinit var binding: FragmentForecastBinding
    private lateinit var act: MainActivity
    private val viewModel: ForecastViewModel by viewModels()
    private var mForecasts: ArrayList<ForecastItem>? = arrayListOf()
    private lateinit var mAdapter: ForecastAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)

        act = activity as MainActivity
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
            weather.observe(viewLifecycleOwner) { data ->
                if (data != null) {
                    updateForecasts(data)
                }
            }
            isLoading.observe(viewLifecycleOwner) { it ->
                if (it) act.showProgress() else act.hideProgress()

            }
        }
    }


    private fun updateForecasts(items: List<ForecastItem>) {
        mForecasts?.clear()
        mForecasts?.addAll(items)
        mAdapter.notifyDataSetChanged()
        act.hideProgress()
    }


}