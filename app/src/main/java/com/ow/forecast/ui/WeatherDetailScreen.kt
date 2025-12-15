package com.ow.forecast.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShieldMoon
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ow.forecast.R
import com.ow.forecast.utilities.Utilities


@Composable
fun WeatherDetailScreen(
    context: Context,
    itemId: Int,
    viewModel: WeatherViewModel
) {

    val weatherItem = viewModel.getForecastById(itemId)
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
        ElevatedCard(
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp, 40.dp, 20.dp, 40.dp)
            ) {
                val formattedDateTime = remember(weatherItem?.dateText) {
                    Utilities.formatDate(weatherItem?.dateText)
                }
                Text(
                    text = formattedDateTime,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )

                Spacer(
                    modifier = Modifier.size(10.dp)
                )

                Row {
                    Image(
                        imageVector = Icons.Filled.ShieldMoon,
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Black),
                        alignment = Alignment.TopStart,
                        colorFilter = ColorFilter.tint(Color.Blue)
                    )

                    Spacer(
                        modifier = Modifier.size(20.dp)
                    )

                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {

                        Text(
                            text = "Temperature: ${weatherItem?.temp?.let { "$it °C" } ?: "--"}",
                            fontSize = 16.sp
                        )

                        Text(
                            text = "Pressure: ${weatherItem?.pressure?.let { "$it hPa" } ?: "--"}",
                            fontSize = 16.sp
                        )

                        Text(
                            text = "Humidity: ${weatherItem?.humidity?.let { "$it %" } ?: "--"}",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}


