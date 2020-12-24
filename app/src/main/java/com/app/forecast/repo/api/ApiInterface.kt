package com.app.forecast.repo.api

import com.app.forecast.models.CityModel
import com.app.forecast.models.WeatherResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/data/2.5/group")
    fun getCities(
        @Query("id", encoded = true) id: String,
        @Query("units") units: String,
        @Query("appid") key: String
    ): Observable<WeatherResponseModel>

    @GET("/data/2.5/weather")
    fun getCityWeather(
        @Query("q") cityName: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Observable<CityModel>

}
