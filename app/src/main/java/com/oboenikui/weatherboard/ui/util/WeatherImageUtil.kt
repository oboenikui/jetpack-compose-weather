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
package com.oboenikui.weatherboard.ui.util

import android.net.Uri
import androidx.annotation.DrawableRes
import com.oboenikui.weatherboard.R
import com.oboenikui.weatherboard.model.weather.WeatherType

object WeatherImageUtil {

    @DrawableRes
    fun getIconRes(weatherType: WeatherType): Int {
        return when (weatherType.icon) {
            "01d" -> R.drawable.ic_weather_clear_day
            "01n" -> R.drawable.ic_weather_clear_night
            "02d" -> R.drawable.ic_weather_few_cloud_day
            "02n" -> R.drawable.ic_weather_few_cloud_night
            "04d", "04n" -> R.drawable.ic_weather_overcast_clouds
            "10d" -> R.drawable.ic_weather_moderate_rain
            else -> 0
        }
    }

    fun getBackgroundUri(weatherType: WeatherType): Uri {
        val url = when (weatherType.icon) {
            "02d" -> "https://images.pexels.com/photos/3888585/pexels-photo-3888585.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
            else -> "https://images.pexels.com/photos/3768/sky-sunny-clouds-cloudy.jpg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" // dummy
        }
        return Uri.parse(url)
    }
}
