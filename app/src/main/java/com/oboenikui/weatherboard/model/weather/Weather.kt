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
package com.oboenikui.weatherboard.model.weather

import java.time.OffsetDateTime

data class CurrentForecast(
    val placeName: String,
    val forecast: Forecast<Temperatures.Hourly>,
)

data class HourlyForecasts(
    val placeName: String,
    val forecasts: List<Forecast<Temperatures.Hourly>>,
)

data class DailyForecasts(
    val placeName: String,
    val forecasts: List<Forecast<Temperatures.Daily>>,
)

data class Forecast<T : Temperatures>(
    val time: OffsetDateTime,
    val temperatures: T,
    // "Current Weather Forecast" doesn't contain precipitation
    val precipitation: Float?,
    val humidity: Float,
    val wind: Wind,
    val weathers: List<WeatherType>,
)

sealed class Temperatures {

    data class Hourly(
        val unit: TemperatureUnit,
        val temperature: Float,
        val feelsLike: Float,
    ) : Temperatures()

    data class Daily(
        val unit: TemperatureUnit,
        val morning: Float,
        val day: Float,
        val evening: Float,
        val night: Float,
        val min: Float,
        val max: Float,
    ) : Temperatures()
}

enum class TemperatureUnit {
    Celsius, Fahrenheit
}

data class Wind(val speed: Float, val deg: Int)

/**
 * These definitions follow OpenWeatherMap
 * https://openweathermap.org/weather-conditions#Weather-Condition-Codes-2
 */
sealed class WeatherType {

    abstract val id: Int
    abstract val description: String
    abstract val icon: String

    data class Thunderstorm(
        override val id: Int,
        override val description: String,
        override val icon: String,
    ) : WeatherType()

    data class Drizzle(
        override val id: Int,
        override val description: String,
        override val icon: String,
    ) : WeatherType()

    data class Rain(
        override val id: Int,
        override val description: String,
        override val icon: String,
    ) : WeatherType()

    data class Snow(
        override val id: Int,
        override val description: String,
        override val icon: String,
    ) : WeatherType()

    data class Atmosphere(
        override val id: Int,
        override val description: String,
        override val icon: String,
    ) : WeatherType()

    data class Clear(
        override val id: Int,
        override val description: String,
        override val icon: String,
    ) : WeatherType()

    data class Clouds(
        override val id: Int,
        override val description: String,
        override val icon: String,
    ) : WeatherType()
}
