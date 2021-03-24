/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oboenikui.weatherboard.ui.feature.dashboard

import android.icu.text.DateTimePatternGenerator
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oboenikui.weatherboard.R
import com.oboenikui.weatherboard.model.dashboard.DashboardColumn
import com.oboenikui.weatherboard.model.dashboard.DashboardQuery
import com.oboenikui.weatherboard.model.weather.Forecast
import com.oboenikui.weatherboard.model.weather.Temperatures
import com.oboenikui.weatherboard.ui.theme.maxTempColor
import com.oboenikui.weatherboard.ui.theme.minTempColor
import com.oboenikui.weatherboard.ui.util.WeatherImageUtil
import com.oboenikui.weatherboard.ui.util.toUnitString
import java.time.format.DateTimeFormatter

private val DashboardQueryItemHeight = 180.dp

@Composable
fun DashboardQueryItem(
    modifier: Modifier = Modifier,
    query: DashboardQuery,
    forecasts: List<Forecast<*>>,
) {
    Box(
        modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(DashboardQueryItemHeight)
    ) {
        Card(Modifier.fillMaxSize()) {
            Column(Modifier.padding(8.dp)) {

                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = query.name,
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Center,
                )
                Spacer(Modifier.height(8.dp))

                when (query.mainColumn) {
                    DashboardColumn.Weather -> WeatherQueryItem(query = query,
                        forecasts = forecasts)
                    DashboardColumn.Date -> DayQueryItem(query = query, forecasts = forecasts)
                    DashboardColumn.Precipitation -> PrecipitationQueryItem(query = query,
                        forecasts = forecasts)
                    else -> DayQueryItem(query = query, forecasts = forecasts)
                }
            }
        }
    }
}

@Composable
private fun WeatherQueryItem(query: DashboardQuery, forecasts: List<Forecast<*>>) {
    val matchedFirstItem = forecasts.firstOrNull()

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (matchedFirstItem == null) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No match item")
            }
        } else {
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = WeatherImageUtil.getIconRes(matchedFirstItem.weathers.first())),
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            @Suppress("UNCHECKED_CAST")
            when (matchedFirstItem.temperatures) {
                is Temperatures.Hourly -> TODO("Not implemented yet")
                is Temperatures.Daily -> DailyTemperaturesBody(matchedFirstItem as Forecast<Temperatures.Daily>, false)
            }
            Text(text = stringResource(R.string.format_precipitation,
                matchedFirstItem.precipitation ?: -1f))
            Text(text = stringResource(R.string.format_humidity, matchedFirstItem.humidity))
            Text(text = stringResource(R.string.format_wind, matchedFirstItem.wind.speed))
        }
    }
}

@Composable
private fun PrecipitationQueryItem(query: DashboardQuery, forecasts: List<Forecast<*>>) {
    val matchedFirstItem = forecasts.firstOrNull()

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (matchedFirstItem == null) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No match item")
            }
        } else {
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "%.1f%%".format(matchedFirstItem.precipitation ?: -1f),
                    style = MaterialTheme.typography.h1,
                    fontSize = 20.sp,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            @Suppress("UNCHECKED_CAST")
            when (matchedFirstItem.temperatures) {
                is Temperatures.Hourly -> TODO("Not implemented yet")
                is Temperatures.Daily -> DailyTemperaturesBody(matchedFirstItem as Forecast<Temperatures.Daily>)
            }
            Text(text = stringResource(R.string.format_humidity, matchedFirstItem.humidity))
            Text(text = stringResource(R.string.format_wind, matchedFirstItem.wind.speed))
        }
    }
}

@Composable
private fun DayQueryItem(query: DashboardQuery, forecasts: List<Forecast<*>>) {
    val matchedFirstItem = forecasts.firstOrNull()

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (matchedFirstItem == null) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No match item")
            }
        } else {
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(8.dp))
                val generator = DateTimePatternGenerator.getInstance()
                Text(
                    text = matchedFirstItem.time.format(
                        DateTimeFormatter.ofPattern(generator.getBestPattern("MMMd"))
                    ),
                    style = MaterialTheme.typography.h1,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            @Suppress("UNCHECKED_CAST")
            when (matchedFirstItem.temperatures) {
                is Temperatures.Hourly -> TODO("Not implemented yet")
                is Temperatures.Daily -> DailyTemperaturesBody(matchedFirstItem as Forecast<Temperatures.Daily>)
            }

            Text(text = stringResource(R.string.format_precipitation,
                matchedFirstItem.precipitation ?: -1f))
        }
    }
}

@Composable
private fun DailyTemperaturesBody(
    forecast: Forecast<Temperatures.Daily>,
    withIcon: Boolean = true,
) {
    val weather = forecast.weathers.first()
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (withIcon) {
            Icon(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(id = WeatherImageUtil.getIconRes(weather)),
                contentDescription = weather.description,
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "%.1f".format(forecast.temperatures.max),
            fontSize = 18.sp,
            color = maxTempColor,
            fontWeight = FontWeight.W600,
        )
        Text(
            text = forecast.temperatures.unit.toUnitString(),
            fontSize = 12.sp,
            color = maxTempColor,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "/", fontSize = 18.sp)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "%.1f".format(forecast.temperatures.min),
            fontSize = 18.sp,
            color = minTempColor,
            fontWeight = FontWeight.W600,
        )
        Text(
            text = forecast.temperatures.unit.toUnitString(),
            fontSize = 12.sp,
            color = minTempColor,
        )
    }
}

@Composable
fun DashboardQueryAddBody(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(DashboardQueryItemHeight)
    ) {
        Card(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .fillMaxSize()
                    .clickable { onClick() }
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(48.dp),
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = stringResource(R.string.label_add_item),
                )
            }
        }
    }
}
