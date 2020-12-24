package com.app.forecast.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TemperatureModel(
    @Json(name = "temp")
    var current: Float,
    @Json(name = "temp_min")
    val minimum: Float,
    @Json(name = "temp_max")
    val maximum: Float
)
