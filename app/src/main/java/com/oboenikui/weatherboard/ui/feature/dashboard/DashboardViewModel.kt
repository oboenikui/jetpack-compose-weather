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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oboenikui.weatherboard.domain.dashboard.ObserveDashboardMainPlaceName
import com.oboenikui.weatherboard.domain.dashboard.ObserveDashboardQueries
import com.oboenikui.weatherboard.domain.weather.GetCurrentForecast
import com.oboenikui.weatherboard.domain.weather.GetHourlyForecasts
import com.oboenikui.weatherboard.domain.weather.GetQueryForecasts
import com.oboenikui.weatherboard.model.dashboard.DashboardQuery
import com.oboenikui.weatherboard.model.onSuccess
import com.oboenikui.weatherboard.model.weather.CurrentForecast
import com.oboenikui.weatherboard.model.weather.Forecast
import com.oboenikui.weatherboard.model.weather.HourlyForecasts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getCurrentForecast: GetCurrentForecast,
    private val getHourlyForecasts: GetHourlyForecasts,
    private val getQueryForecasts: GetQueryForecasts,
    private val observeDashboardMainPlaceName: ObserveDashboardMainPlaceName,
    private val observeDashboardQueries: ObserveDashboardQueries,
) : ViewModel() {

    private val _currentForecast = MutableStateFlow<CurrentForecast?>(null)
    val currentForecast: Flow<CurrentForecast?>
        get() = _currentForecast.filterNotNull()

    private val _hourlyForecasts = MutableStateFlow<HourlyForecasts?>(null)
    val hourlyForecasts: Flow<HourlyForecasts>
        get() = _hourlyForecasts.filterNotNull()

    private val _queryForecasts = MutableStateFlow<List<Pair<DashboardQuery, List<Forecast<*>>>>>(
        emptyList()
    )
    val queryForecasts: Flow<List<Pair<DashboardQuery, List<Forecast<*>>>>>
        get() = _queryForecasts

    private val dashboardQueries = observeDashboardQueries(Unit)

    private val mainPlaceName = observeDashboardMainPlaceName(Unit)

    init {
        dashboardQueries.onEach {
            // TODO: reuse cache if the new query is generated by re-order operation
            refreshQuery(it)
        }.launchIn(viewModelScope)

        mainPlaceName.onEach {
            refreshCurrent(it)
            refreshHourly(it)
        }.launchIn(viewModelScope)
    }

    fun refresh() {
        viewModelScope.launch {
            val currentWeatherTask = async {
                val placeName = mainPlaceName.firstOrNull()
                if (placeName != null) {
                    refreshCurrent(placeName)
                    refreshHourly(placeName)
                }
            }
            val queryWeatherTask = async {
                refreshQuery(dashboardQueries.value)
            }
            currentWeatherTask.await()
            queryWeatherTask.await()
        }
    }

    private suspend fun refreshCurrent(placeName: String) {
        getCurrentForecast(GetCurrentForecast.Params(placeName)).onSuccess {
            _currentForecast.emit(it)
        }
    }

    private suspend fun refreshHourly(placeName: String) {
        getHourlyForecasts(GetHourlyForecasts.Params(placeName)).onSuccess {
            _hourlyForecasts.emit(it)
        }
    }

    private suspend fun refreshQuery(queries: List<DashboardQuery>) {
        getQueryForecasts(GetQueryForecasts.Params(queries)).onSuccess {
            _queryForecasts.emit(it)
        }
    }
}
