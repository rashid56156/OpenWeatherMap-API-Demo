package com.ow.forecast.ui.forecast

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ow.forecast.WeatherApplication
import com.ow.forecast.api.WeatherApi
import com.ow.forecast.databinding.FragmentForecastBinding
import com.ow.forecast.models.ForecastItem
import com.ow.forecast.models.WeatherForecast
import com.ow.forecast.ui.MainActivity
import com.ow.forecast.utilities.AlertView
import javax.inject.Inject
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.PagerSnapHelper

import androidx.recyclerview.widget.SnapHelper

import com.ow.forecast.utilities.JSONResourceReader
import android.os.Looper
import com.ow.forecast.R
import com.ow.forecast.utilities.ConnectionChecker


class ForecastFragment : Fragment(), ForecastView {

    private lateinit var binding: FragmentForecastBinding
    private lateinit var act: MainActivity
    private lateinit var viewModel: ForecastViewModel
    private lateinit var mForecasts: ArrayList<ForecastItem?>
    private lateinit var mAdapter: ForecastAdapter
    private var mLiveMode = true


    @Inject
    lateinit var api: WeatherApi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WeatherApplication.getComponent().inject(this)

        act = activity as MainActivity

        mForecasts = arrayListOf()
        mAdapter = ForecastAdapter(mForecasts, act)

        viewModel = ForecastViewModel(api, this)

        arguments?.let {

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)

        fetchForecast()
        setupUI()

        return binding.root
    }


    private fun setupUI() {
        binding.rvForecast.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvForecast)
        binding.rvForecast.adapter = mAdapter

        binding.swLive.setOnCheckedChangeListener { _, isChecked ->
            mForecasts.clear()
            mAdapter.notifyDataSetChanged()
            mLiveMode = isChecked
            fetchForecast()

        }

        act.hideProgress()
    }



    override fun didGetForecast(response: WeatherForecast) {
        act.runOnUiThread {
            setupAdapter(response)
        }


    }

    override fun errorProcessingRequest(message: String) {
        act.runOnUiThread {
            act.hideProgress()
            AlertView.showErrorMsg(act, message)
        }
    }

    private fun fetchForecast(){
        if(ConnectionChecker.isNetworkConnected(act) && mLiveMode) fetchForecastOnline() else fetchForecastOffline()
    }

    private fun fetchForecastOnline() {
        act.showProgress()
        viewModel.getWeatherForecast()
    }

    private fun fetchForecastOffline() {
        act.showProgress()
        val mainHandler = Handler(Looper.getMainLooper())
        val reader = JSONResourceReader(act.resources)
        val forecast: WeatherForecast = reader.constructUsingGson(WeatherForecast::class.java)
        mainHandler.post(Runnable {
            setupAdapter(forecast)
        })
    }

    private fun setupAdapter(forecast: WeatherForecast){
        mForecasts.clear()
        forecast.list?.let { mForecasts.addAll(it) }
        mAdapter.notifyDataSetChanged()
        act.hideProgress()
    }


}