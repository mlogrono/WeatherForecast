package com.app.forecast.di.components

import com.app.forecast.di.ActivityModule
import com.app.forecast.di.CitiesModule
import com.app.forecast.di.RetrofitModule
import com.app.forecast.views.WeatherDetailsFragment
import dagger.Subcomponent

@Subcomponent(modules = [CitiesModule::class, RetrofitModule::class, ActivityModule::class])
interface WeatherDetailsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): WeatherDetailsComponent
    }

    fun inject(fragment: WeatherDetailsFragment)
}
