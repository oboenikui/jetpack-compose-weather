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
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oboenikui.weatherboard.R
import com.oboenikui.weatherboard.model.dashboard.DashboardColumn
import com.oboenikui.weatherboard.model.dashboard.DashboardQuery
import com.oboenikui.weatherboard.model.weather.Forecast
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
            when (query.mainColumn) {
                DashboardColumn.Date -> DayQueryItem(query = query, forecasts = forecasts)
                else -> DayQueryItem(query = query, forecasts = forecasts)
            }
        }
    }
}

@Composable
fun DashboardQueryAddItem(modifier: Modifier = Modifier, onClick: () -> Unit) {
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

@Composable
private fun DayQueryItem(query: DashboardQuery, forecasts: List<Forecast<*>>) {
    val matchedFirstItem = forecasts.firstOrNull()

    Column(Modifier.padding(8.dp)) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = query.name,
            fontWeight = FontWeight.W600,
            fontSize = 14.sp
        )
        Spacer(Modifier.height(8.dp))
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
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = null,
                )
                val generator = DateTimePatternGenerator.getInstance()
                Text(
                    text = matchedFirstItem.time.format(
                        DateTimeFormatter.ofPattern(
                            generator.getBestPattern(
                                "MMMd"
                            )
                        )
                    )
                )
            }
        }
    }
}
