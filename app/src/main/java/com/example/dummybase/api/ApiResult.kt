package com.example.dummybase.api

import com.example.dummybase.DummyBaseApp
import com.example.dummybase.data.model.ErrorBody

/**
 * A generic class that holds a value with its loading status.
 *
 * Result is usually created by the Repository classes where they return
 * `LiveData<Result<T>>` to pass back the latest data to the UI with its fetch status.
 */
data class ApiResult<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val errorData: Int? = null,
    val errorInfo: List<String>? = null
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ApiResult<T> {
            return ApiResult(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null, errorData: ErrorBody? = null): ApiResult<T> {
            return try {
                val errStringId =
                    if (errorData?.debugMessage != null) {
                        val identifier = DummyBaseApp.appContext.resources.getIdentifier(
                            errorData.debugMessage,
                            "string",
                            DummyBaseApp.appContext.packageName
                        )
                        if (identifier != 0) {
                            identifier
                        } else null
                    } else null
                ApiResult(Status.ERROR, data, message, errStringId, errorData?.details?.infoList)
            } catch (e: Error) {
                ApiResult(Status.ERROR, data, message)
            }
        }

        fun <T> loading(data: T? = null): ApiResult<T> {
            return ApiResult(Status.LOADING, data, null)
        }
    }
}