package com.example.dummybase.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorBody(
    val debugMessage: String? = null,
    val details: ErrorDetails? = null,
    val message: String? = null,
    val status: String? = null,
    val timestamp: String? = null
)


@JsonClass(generateAdapter = true)
data class ErrorDetails(
    val infoList: List<String>?
)