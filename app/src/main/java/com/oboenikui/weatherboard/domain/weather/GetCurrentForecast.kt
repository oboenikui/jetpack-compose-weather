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
package com.oboenikui.weatherboard.domain.weather

import com.oboenikui.weatherboard.di.IoDispatcher
import com.oboenikui.weatherboard.domain.UseCase
import com.oboenikui.weatherboard.model.weather.CurrentForecast
import com.oboenikui.weatherboard.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetCurrentForecast @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: WeatherRepository,
) :
    UseCase<GetCurrentForecast.Params, CurrentForecast>(dispatcher) {

    override suspend fun execute(params: Params): CurrentForecast {
        return CurrentForecast(params.cityName, repository.getCurrentForecast(params.cityName))
    }

    data class Params(val cityName: String)
}
