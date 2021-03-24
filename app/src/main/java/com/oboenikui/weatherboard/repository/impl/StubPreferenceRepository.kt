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

import com.oboenikui.weatherboard.model.dashboard.DashboardColumn
import com.oboenikui.weatherboard.model.dashboard.DashboardQuery
import com.oboenikui.weatherboard.model.dashboard.DashboardQueryFilter
import com.oboenikui.weatherboard.model.dashboard.DashboardTable
import com.oboenikui.weatherboard.repository.PreferenceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class StubPreferenceRepository @Inject constructor() : PreferenceRepository {

    override val placeName: StateFlow<String?>
        get() = MutableStateFlow("Tokyo")

    override fun setPlaceName(placeName: String) {
        // TODO: not implemented
    }

    private val _dashboardQueries = MutableStateFlow(
        listOf(
            STUB_PRECIPITATION_OF_TODAY,
            STUB_TOMORROW_WEATHER,
            STUB_NEXT_LAUNDRY_WEATHER_DAY,
        )
    )
    override var dashboardQueries: StateFlow<List<DashboardQuery>> = _dashboardQueries

    override fun setDashboardQueries(queries: List<DashboardQuery>) {
        _dashboardQueries.value = queries
    }

    companion object {

        val STUB_PRECIPITATION_OF_TODAY = DashboardQuery(
            "Precipitation of today",
            DashboardColumn.Precipitation,
            DashboardTable.Daily
        )
        val STUB_TOMORROW_WEATHER = DashboardQuery(
            "Tomorrow weather",
            DashboardColumn.Weather,
            DashboardTable.Daily,
            skipToday = true
        )
        val STUB_NEXT_LAUNDRY_WEATHER_DAY = DashboardQuery(
            "Next laundry weather day",
            DashboardColumn.Date,
            DashboardTable.Daily,
            filter = DashboardQueryFilter.Builder().apply {
                eq(column = DashboardColumn.Weather, value = 800)
            }.build(),
            skipToday = true
        )
    }
}
