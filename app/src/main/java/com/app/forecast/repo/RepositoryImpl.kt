package com.app.forecast.repo

import com.app.forecast.repo.api.ApiInterface
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: ApiInterface) : Repository {
    override fun getCities(
        cityIds: String,
        units: String,
        key: String) = api.getCities(cityIds, units, key)
    override fun getCityWeather(
        cityName: String,
        units: String,
        key: String) = api.getCityWeather(cityName, units, key)
}