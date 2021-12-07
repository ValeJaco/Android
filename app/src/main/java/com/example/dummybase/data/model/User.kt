package com.example.dummybase.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
class User(
    @field:Json(name = "id") @PrimaryKey var id: Int,
    @field:Json(name = "name") val forename: String,
    @field:Json(name = "codePostal") val lastname: String?,
    @field:Json(name = "email") val email: String,
    //@field:Json(name = "dateDerniereConnexion") val lastConnectionDate: Date,
    @field:Json(name = "isActive") val isActive: Boolean,
    @field:Json(name = "isCoach") val isCoach: Boolean,
) : Parcelable {
}