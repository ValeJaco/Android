package com.example.dummybase.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
class AuthResponseHolder (
    @field:Json(name = "success") val succes: Boolean,
    @field:Json(name = "token") val token: String,
) : Parcelable {

}