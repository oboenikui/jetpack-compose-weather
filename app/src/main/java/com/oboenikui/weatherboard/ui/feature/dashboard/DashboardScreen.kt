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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oboenikui.weatherboard.R
import com.oboenikui.weatherboard.model.weather.CurrentForecast
import com.oboenikui.weatherboard.model.weather.Forecast
import com.oboenikui.weatherboard.model.weather.HourlyForecasts
import com.oboenikui.weatherboard.model.weather.TemperatureUnit
import com.oboenikui.weatherboard.model.weather.Temperatures
import com.oboenikui.weatherboard.model.weather.WeatherType
import com.oboenikui.weatherboard.model.weather.Wind
import com.oboenikui.weatherboard.ui.theme.MyTheme
import com.oboenikui.weatherboard.ui.util.WeatherImageUtil
import com.oboenikui.weatherboard.ui.util.toUnitString
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomAppBar {
                IconButton(
                    onClick = {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Clicked Menu")
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_menu),
                        contentDescription = stringResource(R.string.label_search),
                    )
                }
                Spacer(Modifier.weight(1f, true))
                IconButton(
                    onClick = {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Clicked Search")
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = stringResource(R.string.label_search),
                    )
                }
            }
        }
    ) {

        val queryAndForecasts by viewModel.queryForecasts.collectAsState(initial = emptyList())
        LazyColumn {
            item {
                val current by viewModel.currentForecast.collectAsState(null)
                val hourly by viewModel.hourlyForecasts.collectAsState(null)
                HeaderItem(current = current, hourly = hourly)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(queryAndForecasts.size / 2 + 1) { index ->
                Row {
                    Spacer(modifier = Modifier.width(12.dp))

                    if (queryAndForecasts.size > index * 2) {
                        val (query, forecasts) = queryAndForecasts[index * 2]
                        DashboardQueryItem(
                            modifier = Modifier.weight(1f),
                            query = query,
                            forecasts = forecasts
                        )
                    } else {
                        DashboardQueryAddBody(
                            modifier = Modifier.weight(1f),
                            onClick = {
                            }
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    when {
                        queryAndForecasts.size > index * 2 + 1 -> {
                            val (query, forecasts) = queryAndForecasts[index * 2 + 1]
                            DashboardQueryItem(
                                modifier = Modifier.weight(1f),
                                query = query,
                                forecasts = forecasts
                            )
                        }
                        queryAndForecasts.size == index * 2 + 1 -> {
                            DashboardQueryAddBody(
                                modifier = Modifier.weight(1f),
                                onClick = {
                                }
                            )
                        }
                        else -> {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

private val CurrentItemHeight = 112.dp
private val HourlyItemHeight = 104.dp

@Composable
private fun HeaderItem(current: CurrentForecast?, hourly: HourlyForecasts?) {
    Box(modifier = Modifier.fillMaxWidth()) {
        if (current != null) {
            CoilImage(
                modifier = Modifier.fillMaxWidth()
                    .height(CurrentItemHeight + HourlyItemHeight + 16.dp),
                data = WeatherImageUtil.getBackgroundUri(current.forecast.weathers.first()),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                fadeIn = true,
                colorFilter = ColorFilter.lighting(Color.Gray, Color.Transparent)
            ) {
            }
        }

        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.onPrimary) {
            Column(modifier = Modifier.fillMaxWidth()) {
                HeaderCurrentItem(current = current)
                HeaderHourlyItem(hourly = hourly)
            }
        }
    }
}

@Composable
private fun HeaderCurrentItem(current: CurrentForecast?) {
    Column(
        Modifier
            .padding(16.dp)
            .height(CurrentItemHeight - 16.dp * 2)
    ) {
        if (current == null) {
            Box {}
        } else {
            Row {
                val generator = DateTimePatternGenerator.getInstance()
                Text(
                    text = current.forecast.time.format(
                        DateTimeFormatter.ofPattern(
                            generator.getBestPattern(
                                "MMMdHm"
                            )
                        )
                    ),
                    style = MaterialTheme.typography.caption,
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = current.placeName,
                    style = MaterialTheme.typography.caption,
                )
            }
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.Bottom) {

                Icon(
                    modifier = Modifier.size(58.dp),
                    painter = painterResource(WeatherImageUtil.getIconRes(current.forecast.weathers.first())),
                    contentDescription = current.forecast.weathers.first().description,
                )
                Text(
                    text = "%.1f".format(current.forecast.temperatures.temperature),
                    fontSize = 46.sp,
                    fontWeight = FontWeight.W600,
                )
                Text(
                    modifier = Modifier.paddingFromBaseline(bottom = 12.dp),
                    text = current.forecast.temperatures.unit.toUnitString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                )
            }
        }
    }
}

@Composable
private fun HeaderHourlyItem(hourly: HourlyForecasts?) {
    Column(
        Modifier
            .height(HourlyItemHeight)
            .fillMaxWidth()
    ) {
        if (hourly != null) {
            TemperatureGraph(hourly = hourly)
        } else {
            Spacer(Modifier)
        }
    }
}

@Preview
@Composable
fun HeaderItemPreview() {
    MyTheme {
        HeaderItem(
            current = CurrentForecast(
                placeName = "Tokyo",
                forecast = Forecast(
                    time = OffsetDateTime.now(),
                    humidity = 0.1f,
                    precipitation = null,
                    wind = Wind(speed = 1.1f, deg = 0),
                    weathers = listOf(WeatherType.Clear(800, "clear sky", "01d")),
                    temperatures = Temperatures.Hourly(TemperatureUnit.Celsius, 12.3f, 11.2f),
                )
            ),
            hourly = HourlyForecasts(
                placeName = "Tokyo",
                forecasts = listOf(
                    Forecast(
                        time = OffsetDateTime.now(),
                        humidity = 0.1f,
                        precipitation = null,
                        wind = Wind(speed = 1.1f, deg = 0),
                        weathers = listOf(WeatherType.Clear(800, "clear sky", "01d")),
                        temperatures = Temperatures.Hourly(TemperatureUnit.Celsius, 12.3f, 11.2f),
                    ),
                )
            )
        )
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun DashboardScreenPreview() {
    MyTheme {
        Surface {
            DashboardScreen()
        }
    }
}
