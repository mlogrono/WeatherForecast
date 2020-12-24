package com.app.forecast.di

import com.app.forecast.di.components.CitiesComponent
import com.app.forecast.di.components.WeatherDetailsComponent
import dagger.Module

@Module(
    subcomponents = [
        CitiesComponent::class,
        WeatherDetailsComponent::class
    ]
)
class Subcomponents