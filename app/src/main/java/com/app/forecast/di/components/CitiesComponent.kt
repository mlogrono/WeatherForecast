package com.app.forecast.di.components

import com.app.forecast.di.ActivityModule
import com.app.forecast.di.CitiesModule
import com.app.forecast.di.RetrofitModule
import com.app.forecast.views.CitiesFragment
import dagger.Subcomponent

@Subcomponent(modules = [RetrofitModule::class, CitiesModule::class, ActivityModule::class])
interface CitiesComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): CitiesComponent
    }

    fun inject(fragment: CitiesFragment)
}
