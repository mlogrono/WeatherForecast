package com.app.forecast.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityModel (
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "weather")
    val weather: List<WeatherModel>,
    @Json(name = "main")
    val temperature: TemperatureModel,
)