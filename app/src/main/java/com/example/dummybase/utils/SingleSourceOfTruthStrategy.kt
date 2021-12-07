package com.example.dummybase.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.dummybase.api.ApiResult
import kotlinx.coroutines.Dispatchers

/**
 * The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [ApiResult.Status.SUCCESS] - with data from database
 * [ApiResult.Status.ERROR] - if error has occurred from any source
 * [ApiResult.Status.LOADING]
 */
fun <T, A> resultLiveData(
    databaseQuery: (() -> LiveData<T>),
    networkCall: (suspend () -> ApiResult<A>)? = null,
    saveCallResult: (suspend (A) -> Unit)? = null
): LiveData<ApiResult<T>> =
    liveData(Dispatchers.IO) {
        emit(ApiResult.loading())
        val source = databaseQuery.invoke().map { ApiResult.success(it) }
        emitSource(source)
        if (networkCall != null) {
            val responseStatus = networkCall.invoke()
            if (responseStatus.status == ApiResult.Status.SUCCESS && saveCallResult != null) {
                try {
                    saveCallResult(responseStatus.data!!)
                } catch (err: Error) {
                    emit(ApiResult.error(err.message!!))
                }
            } else if (responseStatus.status == ApiResult.Status.ERROR) {
                emit(ApiResult.error(responseStatus.message!!))
                emitSource(source)
            }
        }
    }