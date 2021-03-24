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
package com.oboenikui.weatherboard.domain

import com.oboenikui.weatherboard.model.AppException
import com.oboenikui.weatherboard.model.ErrorType
import com.oboenikui.weatherboard.model.Result
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

abstract class UseCase<Params, Out>(private val context: CoroutineContext) {

    suspend operator fun invoke(params: Params): Result<Out> {
        return withContext(context) {
            try {
                Result.Success(execute(params))
            } catch (e: AppException) {
                Result.Failure(e)
            } catch (e: CancellationException) {
                // don't catch CancellationException
                throw e
            } catch (e: Exception) {
                Result.Failure(AppException(type = ErrorType.Other, throwable = e))
            }
        }
    }

    abstract suspend fun execute(params: Params): Out
}
