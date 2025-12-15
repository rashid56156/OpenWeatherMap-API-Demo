package com.ow.forecast.ui

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ow.forecast.R
import com.ow.forecast.models.ForecastUiModel

@Composable
fun WeatherList(context: Context, forecastList: List<ForecastUiModel>, onClick: (ForecastUiModel) -> Unit) {
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