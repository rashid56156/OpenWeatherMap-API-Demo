package com.ow.forecast.ui

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ow.forecast.R
import com.ow.forecast.api.ApiResponse
import com.ow.forecast.models.ForecastUiModel

@Composable
fun WeatherList(context: Context, forecastList: List<ForecastUiModel>,
                onClick: (ForecastUiModel) -> Unit) {
    Column {

        Spacer(
            Modifier.size(40.dp)
        )

        Text(
            text = context.getString(R.string.app_name),
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center

        )

        Spacer(
            Modifier.size(20.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(forecastList) { item ->
                WeatherListItem(
                    weatherItem = item
                ) {
                    onClick(item)
                }
            }
        }
    }
}

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel, onClick: (ForecastUiModel) -> Unit
) {
    // lifecycle-aware collection to a Compose State
    val result by viewModel.weatherResult.collectAsStateWithLifecycle()

    when (result) {
        is ApiResponse.Loading -> {
            // render loading UI
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ApiResponse.Success -> {
            val list = viewModel.forecastCache.collectAsState().value
            WeatherList(LocalContext.current, list, onClick = onClick)
        }

        is ApiResponse.Error -> {
            // optionally show error UI; toast already shown via LaunchedEffect
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.fetching_contributors_error))
            }
        }
    }
}