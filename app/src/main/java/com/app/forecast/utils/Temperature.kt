package com.app.forecast.utils

import android.graphics.Color
import java.lang.Float.NEGATIVE_INFINITY
import java.lang.Float.POSITIVE_INFINITY

enum class Temperature(val tint: Int, private val temperatureRange: ClosedFloatingPointRange<Float>) {

    FREEZING(Color.parseColor("#1976D2"),NEGATIVE_INFINITY..0f),
    COLD(Color.parseColor("#26C6DA"),0.1f..15f),
    WARM(Color.parseColor("#66BB6A"), 15.1f..30f),
    HOT(Color.parseColor("#FF7043"), 30.1f..POSITIVE_INFINITY);

    operator fun contains(temperature: Float): Boolean {
        return temperature in temperatureRange
    }
}