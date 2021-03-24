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
package com.oboenikui.weatherboard.repository.impl

import com.oboenikui.weatherboard.model.dashboard.DashboardQuery
import com.oboenikui.weatherboard.model.weather.Forecast
import com.oboenikui.weatherboard.model.weather.TemperatureUnit
import com.oboenikui.weatherboard.model.weather.Temperatures
import com.oboenikui.weatherboard.model.weather.WeatherType
import com.oboenikui.weatherboard.model.weather.Wind
import com.oboenikui.weatherboard.repository.WeatherRepository
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import javax.inject.Inject

class StubWeatherRepository @Inject constructor() : WeatherRepository {

    override suspend fun getCurrentForecast(cityName: String): Forecast<Temperatures.Hourly> {
        return Forecast(
            weathers = listOf(WeatherType.Clouds(801, "few clouds", "02d")),
            wind = Wind(speed = 2.57f, deg = 180),
            humidity = 33f,
            precipitation = null,
            temperatures = Temperatures.Hourly(
                TemperatureUnit.Celsius,
                14.07f,
                10.02f
            ),
            time = OffsetDateTime.ofInstant(
                Instant.ofEpochSecond(1616488109),
                ZoneId.systemDefault()
            ),
        )
    }

    override suspend fun getHourlyForecasts(cityName: String): List<Forecast<Temperatures.Hourly>> {
        return listOf(
            Forecast(
                weathers = listOf(WeatherType.Clouds(801, "few clouds", "02d")),
                wind = Wind(speed = 4.83f, deg = 185),
                humidity = 33f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    14.07000000000005f,
                    8.430000000000007f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616486400),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(801, "few clouds", "02n")),
                wind = Wind(speed = 5.4f, deg = 188),
                humidity = 36f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    13.560000000000002f,
                    7.6200000000000045f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616490000),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01n")),
                wind = Wind(speed = 5.76f, deg = 184),
                humidity = 42f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    12.950000000000045f,
                    6.980000000000018f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616493600),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01n")),
                wind = Wind(speed = 5.42f, deg = 180),
                humidity = 48f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    12.610000000000014f,
                    7.1200000000000045f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616497200),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01n")),
                wind = Wind(speed = 4.95f, deg = 175),
                humidity = 51f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    12.390000000000043f,
                    7.340000000000032f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616500800),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01n")),
                wind = Wind(speed = 4.33f, deg = 176),
                humidity = 54f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    12.270000000000039f,
                    7.78000000000003f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616504400),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01n")),
                wind = Wind(speed = 3.49f, deg = 188),
                humidity = 54f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    12.210000000000036f,
                    8.300000000000011f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616508000),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01n")),
                wind = Wind(speed = 2.88f, deg = 189),
                humidity = 53f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    12.060000000000002f,
                    8.5f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616511600),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01n")),
                wind = Wind(speed = 2.5f, deg = 192),
                humidity = 54f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    11.810000000000002f,
                    8.520000000000039f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616515200),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01n")),
                wind = Wind(speed = 1.9f, deg = 195),
                humidity = 55f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    11.450000000000045f,
                    8.57000000000005f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616518800),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01n")),
                wind = Wind(speed = 1.54f, deg = 205),
                humidity = 55f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    11.210000000000036f,
                    8.54000000000002f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616522400),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01n")),
                wind = Wind(speed = 1.05f, deg = 228),
                humidity = 56f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    10.980000000000018f,
                    8.660000000000025f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616526000),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01n")),
                wind = Wind(speed = 1.07f, deg = 274),
                humidity = 55f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    10.730000000000018f,
                    8.32000000000005f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616529600),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01d")),
                wind = Wind(speed = 1.64f, deg = 305),
                humidity = 55f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    10.439999999999998f,
                    7.580000000000041f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616533200),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01d")),
                wind = Wind(speed = 1.96f, deg = 317),
                humidity = 52f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    10.760000000000048f,
                    7.600000000000023f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616536800),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01d")),
                wind = Wind(speed = 1.76f, deg = 309),
                humidity = 49f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    11.5f,
                    8.460000000000036f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616540400),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01d")),
                wind = Wind(speed = 1.04f, deg = 228),
                humidity = 47f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    12.390000000000043f,
                    9.890000000000043f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616544000),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01d")),
                wind = Wind(speed = 2.8f, deg = 190),
                humidity = 45f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    13.510000000000048f,
                    9.840000000000032f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616547600),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01d")),
                wind = Wind(speed = 4.03f, deg = 190),
                humidity = 40f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    14.75f,
                    10.140000000000043f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616551200),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01d")),
                wind = Wind(speed = 4.32f, deg = 188),
                humidity = 35f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    15.760000000000048f,
                    10.800000000000011f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616554800),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01d")),
                wind = Wind(speed = 4.79f, deg = 181),
                humidity = 34f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    16.560000000000002f,
                    11.32000000000005f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616558400),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01d")),
                wind = Wind(speed = 5.79f, deg = 182),
                humidity = 36f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    17.150000000000034f,
                    11.420000000000016f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616562000),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01d")),
                wind = Wind(speed = 6.42f, deg = 175),
                humidity = 42f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    17.129999999999995f,
                    11.340000000000032f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616565600),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 6.78f, deg = 178),
                humidity = 45f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    16.83000000000004f,
                    10.920000000000016f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616569200),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 7.53f, deg = 183),
                humidity = 46f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    16.480000000000018f,
                    10.050000000000011f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616572800),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04n")),
                wind = Wind(speed = 6.92f, deg = 183),
                humidity = 49f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    16.100000000000023f,
                    10.210000000000036f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616576400),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04n")),
                wind = Wind(speed = 6.6f, deg = 189),
                humidity = 50f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    15.760000000000048f,
                    10.090000000000032f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616580000),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04n")),
                wind = Wind(speed = 6.88f, deg = 197),
                humidity = 53f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    15.200000000000045f,
                    9.400000000000034f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616583600),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04n")),
                wind = Wind(speed = 6.89f, deg = 198),
                humidity = 56f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    14.810000000000002f,
                    9.090000000000032f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616587200),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04n")),
                wind = Wind(speed = 6.77f, deg = 197),
                humidity = 58f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    14.470000000000027f,
                    8.879999999999995f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616590800),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04n")),
                wind = Wind(speed = 6.76f, deg = 197),
                humidity = 60f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    14.180000000000007f,
                    8.640000000000043f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616594400),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04n")),
                wind = Wind(speed = 6.68f, deg = 200),
                humidity = 61f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    13.950000000000045f,
                    8.480000000000018f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616598000),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04n")),
                wind = Wind(speed = 6.49f, deg = 201),
                humidity = 62f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    13.78000000000003f,
                    8.450000000000045f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616601600),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04n")),
                wind = Wind(speed = 6.04f, deg = 206),
                humidity = 63f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    13.640000000000043f,
                    8.650000000000034f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616605200),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04n")),
                wind = Wind(speed = 5.88f, deg = 204),
                humidity = 63f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    13.640000000000043f,
                    8.760000000000048f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616608800),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04n")),
                wind = Wind(speed = 5.31f, deg = 209),
                humidity = 63f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    13.430000000000007f,
                    8.910000000000025f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616612400),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04n")),
                wind = Wind(speed = 5.26f, deg = 210),
                humidity = 63f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    13.189999999999998f,
                    8.650000000000034f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616616000),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 4.18f, deg = 221),
                humidity = 63f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    13.07000000000005f,
                    9.270000000000039f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616619600),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 3.85f, deg = 216),
                humidity = 60f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    13.29000000000002f,
                    9.610000000000014f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616623200),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 3.59f, deg = 234),
                humidity = 56f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    13.840000000000032f,
                    10.240000000000009f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616626800),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 2.88f, deg = 223),
                humidity = 54f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    14.110000000000014f,
                    10.960000000000036f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616630400),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 3.54f, deg = 208),
                humidity = 51f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    14.879999999999995f,
                    11.240000000000009f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616634000),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 3.39f, deg = 214),
                humidity = 47f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    15.490000000000009f,
                    11.840000000000032f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616637600),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 4.13f, deg = 202),
                humidity = 46f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    15.520000000000039f,
                    11.300000000000011f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616641200),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 3.55f, deg = 208),
                humidity = 44f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    15.900000000000034f,
                    12.03000000000003f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616644800),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 3.18f, deg = 193),
                humidity = 44f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    15.890000000000043f,
                    12.28000000000003f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616648400),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 2.99f, deg = 187),
                humidity = 46f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    15.870000000000005f,
                    12.510000000000048f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616652000),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 3.58f, deg = 177),
                humidity = 47f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    16f,
                    12.310000000000002f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616655600),
                    ZoneId.systemDefault()
                ),
            ),
        )
    }

    override suspend fun getDailyForecasts(cityName: String): List<Forecast<Temperatures.Daily>> {
        return listOf(
            Forecast(
                weathers = listOf(WeatherType.Clouds(500, "light rain", "10d")),
                wind = Wind(speed = 0.55f, deg = 50),
                humidity = 32f,
                precipitation = 0.71f,
                temperatures = Temperatures.Daily(
                    TemperatureUnit.Celsius,
                    7.99f,
                    11.04f,
                    14.07f,
                    12.21f,
                    14.07f,
                    7.88f,
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616464800),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01d")),
                wind = Wind(speed = 4.03f, deg = 190),
                humidity = 40f,
                precipitation = 0f,
                temperatures = Temperatures.Daily(
                    TemperatureUnit.Celsius,
                    10.73f,
                    14.75f,
                    16.48f,
                    14.18f,
                    17.15f,
                    10.44f,
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616551200),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 3.39f, deg = 214),
                humidity = 47f,
                precipitation = 0.01f,
                temperatures = Temperatures.Daily(
                    TemperatureUnit.Celsius,
                    13.19f,
                    15.49f,
                    15.86f,
                    14.86f,
                    16.00f,
                    13.07f,
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616637600),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(800, "clear sky", "01d")),
                wind = Wind(speed = 2.76f, deg = 0),
                humidity = 28f,
                precipitation = 0.18f,
                temperatures = Temperatures.Daily(
                    TemperatureUnit.Celsius,
                    12.59f,
                    17.58f,
                    17.48f,
                    14.49f,
                    18.36f,
                    12.40f,
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616724000),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 5.66f, deg = 185),
                humidity = 53f,
                precipitation = 0f,
                temperatures = Temperatures.Daily(
                    TemperatureUnit.Celsius,
                    11.81f,
                    15.61f,
                    16.11f,
                    15.61f,
                    16.70f,
                    11.81f,
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616810400),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(500, "light rain", "10d")),
                wind = Wind(speed = 11.14f, deg = 191),
                humidity = 54f,
                precipitation = 0.25f,
                temperatures = Temperatures.Daily(
                    TemperatureUnit.Celsius,
                    14.67f,
                    18.37f,
                    16.79f,
                    16.41f,
                    18.37f,
                    14.67f,
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616896800),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(501, "moderate rain", "10d")),
                wind = Wind(speed = 12.58f, deg = 176),
                humidity = 88f,
                precipitation = 1f,
                temperatures = Temperatures.Daily(
                    TemperatureUnit.Celsius,
                    15.90f,
                    16.24f,
                    17.00f,
                    16.53f,
                    17.30f,
                    15.21f,
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616983200),
                    ZoneId.systemDefault()
                ),
            ),
            Forecast(
                weathers = listOf(WeatherType.Clouds(804, "overcast clouds", "04d")),
                wind = Wind(speed = 4.24f, deg = 43),
                humidity = 46f,
                precipitation = 0.06f,
                temperatures = Temperatures.Daily(
                    TemperatureUnit.Celsius,
                    13.96f,
                    17.85f,
                    16.99f,
                    15.10f,
                    19.14f,
                    13.96f,
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1617069600),
                    ZoneId.systemDefault()
                ),
            ),
        )
    }

    override suspend fun getHourlyQueryForecasts(query: DashboardQuery): List<Forecast<Temperatures.Hourly>> {
        return listOf(
            Forecast(
                weathers = listOf(WeatherType.Clouds(801, "few clouds", "02n")),
                wind = Wind(speed = 5.4f, deg = 188),
                humidity = 36f,
                precipitation = null,
                temperatures = Temperatures.Hourly(
                    TemperatureUnit.Celsius,
                    13.560000000000002f,
                    7.6200000000000045f
                ),
                time = OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1616490000),
                    ZoneId.systemDefault()
                ),
            )
        )
    }

    override suspend fun getDailyQueryForecasts(query: DashboardQuery): List<Forecast<Temperatures.Daily>> {
        return when (query) {
            StubPreferenceRepository.STUB_PRECIPITATION_OF_TODAY -> listOf(
                Forecast(
                    weathers = listOf(WeatherType.Clouds(500, "light rain", "10d")),
                    wind = Wind(speed = 0.55f, deg = 50),
                    humidity = 32f,
                    precipitation = 0.71f,
                    temperatures = Temperatures.Daily(
                        TemperatureUnit.Celsius,
                        7.99f,
                        11.04f,
                        14.07f,
                        12.21f,
                        14.07f,
                        7.88f,
                    ),
                    time = OffsetDateTime.ofInstant(
                        Instant.ofEpochSecond(1616464800),
                        ZoneId.systemDefault()
                    ),
                )
            )
            StubPreferenceRepository.STUB_TOMORROW_WEATHER -> listOf(
                Forecast(
                    weathers = listOf(WeatherType.Clouds(800, "clear sky", "01d")),
                    wind = Wind(speed = 4.03f, deg = 190),
                    humidity = 40f,
                    precipitation = 0f,
                    temperatures = Temperatures.Daily(
                        TemperatureUnit.Celsius,
                        10.73f,
                        14.75f,
                        16.48f,
                        14.18f,
                        17.15f,
                        10.44f,
                    ),
                    time = OffsetDateTime.ofInstant(
                        Instant.ofEpochSecond(1616551200),
                        ZoneId.systemDefault()
                    ),
                )
            )
            StubPreferenceRepository.STUB_NEXT_LAUNDRY_WEATHER_DAY -> listOf(
                Forecast(
                    weathers = listOf(WeatherType.Clouds(800, "clear sky", "01d")),
                    wind = Wind(speed = 4.03f, deg = 190),
                    humidity = 40f,
                    precipitation = 0f,
                    temperatures = Temperatures.Daily(
                        TemperatureUnit.Celsius,
                        10.73f,
                        14.75f,
                        16.48f,
                        14.18f,
                        17.15f,
                        10.44f,
                    ),
                    time = OffsetDateTime.ofInstant(
                        Instant.ofEpochSecond(1616551200),
                        ZoneId.systemDefault()
                    ),
                )
            )
            else -> listOf(
                Forecast(
                    weathers = listOf(WeatherType.Clouds(500, "light rain", "10d")),
                    wind = Wind(speed = 0.55f, deg = 50),
                    humidity = 32f,
                    precipitation = 0.71f,
                    temperatures = Temperatures.Daily(
                        TemperatureUnit.Celsius,
                        7.99f,
                        11.04f,
                        14.07f,
                        12.21f,
                        14.07f,
                        7.88f,
                    ),
                    time = OffsetDateTime.ofInstant(
                        Instant.ofEpochSecond(1616464800),
                        ZoneId.systemDefault()
                    ),
                )
            )
        }
    }
}
