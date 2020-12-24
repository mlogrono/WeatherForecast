package com.app.forecast.di.components

import android.content.Context
import com.app.forecast.di.Subcomponents
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    Subcomponents::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun citiesComponent(): CitiesComponent.Factory
    fun weatherDetailsComponent(): WeatherDetailsComponent.Factory
}
