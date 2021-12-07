package com.example.dummybase.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
class Seance (
    @field:Json(name = "id") @PrimaryKey var id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "dateSeance") val dateSeance: Instant,
) : Parcelable {

    public fun dateSeanceToStringForSeanceCard(): String {
        val formatter = DateTimeFormatter.ofPattern( "E d MMM HH:mm" )
            .withLocale(Locale.FRANCE)
            .withZone(ZoneId.systemDefault())

        return formatter.format(dateSeance)
    }
}