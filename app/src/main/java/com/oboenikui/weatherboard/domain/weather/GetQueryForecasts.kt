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
import com.oboenikui.weatherboard.model.dashboard.DashboardQuery
import com.oboenikui.weatherboard.model.dashboard.DashboardTable
import com.oboenikui.weatherboard.model.weather.Forecast
import com.oboenikui.weatherboard.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetQueryForecasts @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: WeatherRepository,
) :
    UseCase<GetQueryForecasts.Params, List<Pair<DashboardQuery, List<Forecast<*>>>>>(dispatcher) {

    override suspend fun execute(params: Params): List<Pair<DashboardQuery, List<Forecast<*>>>> {
        // TODO: reduce N+1
        return params.queries.map {
            it to when (it.from) {
                DashboardTable.Daily -> repository.getDailyQueryForecasts(it)
                DashboardTable.Hourly -> repository.getHourlyQueryForecasts(it)
            }
        }
    }

    data class Params(val queries: List<DashboardQuery>)
}
