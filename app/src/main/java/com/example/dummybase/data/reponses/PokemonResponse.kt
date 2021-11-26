package com.example.dummybase.data.reponses

import com.example.dummybase.data.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PokemonResponse(
    @field:Json(name = "count") val count: Long? = 0,
    @field:Json(name = "results") val results: List<User> = listOf(),
)