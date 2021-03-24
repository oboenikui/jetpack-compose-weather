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
package com.oboenikui.weatherboard.model

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure<T>(val error: AppException) : Result<T>()
}

inline fun <T> Result<T>.onSuccess(action: (data: T) -> Unit): Result<T> {
    if (this is Result.Success) {
        action(this.data)
    }
    return this
}

inline fun <T> Result<T>.onFailure(action: (error: AppException) -> Unit): Result<T> {
    if (this is Result.Failure) {
        action(this.error)
    }
    return this
}

fun <T> Result<T>.getOrNull(): T? {
    return if (this is Result.Success) data else null
}

fun <T> Result<T>.errorOrNull(): AppException? {
    return if (this is Result.Failure) error else null
}
