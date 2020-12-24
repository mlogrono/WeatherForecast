package com.app.forecast.repo

import com.app.forecast.models.CityModel
import com.app.forecast.models.WeatherResponseModel
import io.reactivex.Observable

interface Repository {
    fun getCities(cityIds: String, units: String, key: String): Observable<WeatherResponseModel>
    fun getCityWeather(cityName: String, units: String, key: String): Observable<CityModel>
}