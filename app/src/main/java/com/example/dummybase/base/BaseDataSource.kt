package com.example.dummybase.base

import com.example.dummybase.data.model.ErrorBody
import com.example.dummybase.di.ApiModule.moshi
import retrofit2.Response
import timber.log.Timber
import java.net.SocketTimeoutException
import com.example.dummybase.api.ApiResult

/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ApiResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return ApiResult.success(body)
            }
            val errString = response.errorBody()?.string()
            val errObj: ErrorBody? =
                errString?.let { moshi.adapter(ErrorBody::class.java).fromJson(it) }
            return error("${response.code()} ${response.message()}", errObj)
        } catch (e: Exception) {
            e.printStackTrace()
            return when (e) {
                is SocketTimeoutException -> error("408 " + (e.message ?: e.toString()))
                else -> error(e.message ?: e.toString())
            }
        }
    }

    private fun <T> error(message: String, extra: ErrorBody? = null): ApiResult<T> {
        Timber.e(message)
        return ApiResult.error("$message | Network call has failed".trim(), errorData = extra)
    }

}
