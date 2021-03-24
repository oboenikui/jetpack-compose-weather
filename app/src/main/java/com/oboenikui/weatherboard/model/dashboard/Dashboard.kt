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
package com.oboenikui.weatherboard.model.dashboard

data class DashboardQuery(
    val name: String,
    val mainColumn: DashboardColumn,
    val from: DashboardTable,
    val filter: DashboardQueryFilter = DashboardQueryFilter.Builder().build(),
    val skipToday: Boolean = false,
    // If empty, use default
    val placeName: String? = null,
)

class DashboardQueryFilter private constructor(val comparisons: List<Comparison>) {

    class Builder {

        private val comparisons = mutableListOf<Comparison>()

        fun eq(column: DashboardColumn, value: Number) {
            assert(comparisons.none { it.column == column })
            comparisons += Comparison.Equals(column, value)
        }

        fun gt(column: DashboardColumn, value: Number) {
            assert(
                comparisons.none {
                    (it is Comparison.GreaterThan || it is Comparison.GreaterThanOrEqual || it is Comparison.Equals) &&
                        it.column == column
                }
            )
            comparisons += Comparison.GreaterThan(column, value)
        }

        fun gtOrEq(column: DashboardColumn, value: Number) {
            assert(
                comparisons.none {
                    (it is Comparison.GreaterThan || it is Comparison.GreaterThanOrEqual || it is Comparison.Equals) &&
                        it.column == column
                }
            )

            comparisons += Comparison.GreaterThanOrEqual(column, value)
        }

        fun lt(column: DashboardColumn, value: Number) {
            assert(
                comparisons.none {
                    (it is Comparison.LessThan || it is Comparison.LessThanOrEqual || it is Comparison.Equals) &&
                        it.column == column
                }
            )

            comparisons += Comparison.LessThan(column, value)
        }

        fun ltOrEq(column: DashboardColumn, value: Number) {
            assert(
                comparisons.none {
                    (it is Comparison.LessThan || it is Comparison.LessThanOrEqual || it is Comparison.Equals) &&
                        it.column == column
                }
            )

            comparisons += Comparison.LessThanOrEqual(column, value)
        }

        fun build(): DashboardQueryFilter = DashboardQueryFilter(comparisons.toList())
    }

    sealed class Comparison {

        abstract val column: DashboardColumn
        abstract val value: Number

        data class Equals(
            override val column: DashboardColumn,
            override val value: Number,
        ) : Comparison()

        data class GreaterThan(
            override val column: DashboardColumn,
            override val value: Number,
        ) : Comparison()

        data class GreaterThanOrEqual(
            override val column: DashboardColumn,
            override val value: Number,
        ) : Comparison()

        data class LessThan(
            override val column: DashboardColumn,
            override val value: Number,
        ) : Comparison()

        data class LessThanOrEqual(
            override val column: DashboardColumn,
            override val value: Number,
        ) : Comparison()
    }
}

enum class DashboardTable {
    Daily, Hourly,
}

enum class DashboardColumn {
    Weather, Temperature, MaxTemperature, MinTemperature, Humidity, Precipitation, Wind, Date, DateTime,
}
