package com.app.forecast.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherModel(
    @Json(name = "main")
    val skies: String
)
