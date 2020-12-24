package com.app.forecast

import android.app.Application
import com.app.forecast.di.components.AppComponent
import com.app.forecast.di.components.DaggerAppComponent

open class App : Application() {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}
