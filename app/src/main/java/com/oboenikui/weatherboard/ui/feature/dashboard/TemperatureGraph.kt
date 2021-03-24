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
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oboenikui.weatherboard.model.weather.Forecast
import com.oboenikui.weatherboard.model.weather.HourlyForecasts
import com.oboenikui.weatherboard.model.weather.TemperatureUnit
import com.oboenikui.weatherboard.model.weather.Temperatures
import com.oboenikui.weatherboard.model.weather.WeatherType
import com.oboenikui.weatherboard.model.weather.Wind
import com.oboenikui.weatherboard.ui.theme.MyTheme
import com.oboenikui.weatherboard.ui.util.WeatherIconUtil
import com.oboenikui.weatherboard.ui.util.toUnitString
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlin.math.sin

private val TemperatureGraphTopOffset = 16.dp
private val TemperatureGraphHeight = 40.dp
private val TemperatureGraphWeatherAndDateHeight = 56.dp
private val TemperatureGraphLayoutHeight =
    TemperatureGraphHeight + TemperatureGraphTopOffset + TemperatureGraphWeatherAndDateHeight
private val TemperatureGraphLineWidth = 2.dp
private val TemperatureGraphXInterval = 40.dp
private val TemperatureGraphLabelSize = 10.sp

@Composable
fun TemperatureGraph(
    modifier: Modifier = Modifier,
    hourly: HourlyForecasts,
    lineColor: Color? = null,
) {
    val forecasts = hourly.forecasts
    if (forecasts.isEmpty()) {
        return
    }
    val max = forecasts.maxOf { it.temperatures.temperature } + 1
    val min = forecasts.minOf { it.temperatures.temperature } - 1
    val color = lineColor ?: MaterialTheme.colors.onSurface
    Box(modifier = modifier.horizontalScroll(rememberScrollState())) {
        Box(
            modifier = modifier
                .width(TemperatureGraphXInterval * forecasts.size)
                .height(TemperatureGraphLayoutHeight),
        ) {
            Canvas(
                modifier = modifier
                    .align(Alignment.TopCenter)
                    .padding(TemperatureGraphTopOffset)
                    .width(TemperatureGraphXInterval * forecasts.size)
                    .height(TemperatureGraphHeight),
                onDraw = {
                    drawGraphLine(forecasts, max = max, min = min, color = color)
                }
            )
            Row {
                val ratio = TemperatureGraphHeight / (max - min)
                for (weather in forecasts) {
                    Box(
                        modifier = modifier
                            .width(TemperatureGraphXInterval)
                            .height(TemperatureGraphLayoutHeight),
                        contentAlignment = Alignment.TopCenter,
                    ) {
                        Text(
                            modifier = modifier.padding(top = ratio * (max - weather.temperatures.temperature)),
                            text = "%.1f%s".format(
                                weather.temperatures.temperature,
                                weather.temperatures.unit.toUnitString()
                            ),
                            fontSize = TemperatureGraphLabelSize,
                            style = MaterialTheme.typography.caption,
                        )
                        Column(
                            modifier = modifier
                                .align(Alignment.BottomCenter),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(WeatherIconUtil.getIconRes(weather.weathers.first())),
                                contentDescription = weather.weathers.first().description,
                                modifier = modifier.size(16.dp),
                            )
                            if (weather.time.toLocalTime().hour == 0) {
                                val generator = DateTimePatternGenerator.getInstance()
                                Text(
                                    text = weather.time.format(
                                        DateTimeFormatter.ofPattern(
                                            generator.getBestPattern("Md")
                                        )
                                    ) +
                                        "\n" +
                                        weather.time.format(
                                            DateTimeFormatter.ofPattern(
                                                generator.getBestPattern("Hm")
                                            )
                                        ),
                                    fontSize = TemperatureGraphLabelSize,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.caption,
                                )
                            } else {
                                Text(
                                    modifier = Modifier.padding(top = 12.dp),
                                    text = weather.time.toLocalTime()
                                        .format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)),
                                    fontSize = TemperatureGraphLabelSize,
                                    style = MaterialTheme.typography.caption,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun DrawScope.drawGraphLine(
    weathers: List<Forecast<Temperatures.Hourly>>,
    max: Float,
    min: Float,
    color: Color,
) {
    val ratio = TemperatureGraphHeight.toPx() / (max - min)
    val interval = TemperatureGraphXInterval.toPx()
    val lineWidth = TemperatureGraphLineWidth.toPx()
    var nextStartX = -interval / 2
    var nextStartY = (max - weathers[0].temperatures.temperature) * ratio

    for ((_, temperature) in weathers) {
        val currentEndY = (max - temperature.temperature) * ratio
        val currentEndX = nextStartX + interval
        drawLine(
            color,
            Offset(nextStartX, nextStartY),
            Offset(currentEndX, currentEndY),
            strokeWidth = lineWidth,
        )
        nextStartX = currentEndX
        nextStartY = currentEndY
    }

    drawLine(
        color,
        Offset(nextStartX, nextStartY),
        Offset(nextStartX + interval, nextStartY),
        strokeWidth = lineWidth,
    )
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun TemperatureGraphPreview() {
    fun dummyWeather(time: OffsetDateTime, temperature: Float) =
        Forecast(
            time = time,
            temperatures = Temperatures.Hourly(
                TemperatureUnit.Celsius,
                temperature,
                temperature,
            ),
            humidity = 0f,
            precipitation = 0f,
            wind = Wind(0f, 0),
            weathers = listOf(WeatherType.Clear(800, "clear sky", "01d")),
        )
    MyTheme {
        TemperatureGraph(
            hourly = HourlyForecasts(
                placeName = "Tokyo",
                forecasts = (0..23).map { hour ->
                    dummyWeather(
                        time = OffsetDateTime.parse("2020-03-21T00:00:00+09:00")
                            .plusHours(hour.toLong()),
                        temperature = 10f + sin(hour.toFloat()) * 9f, // Draw sign curve
                    )
                }
            )
        )
    }
}
