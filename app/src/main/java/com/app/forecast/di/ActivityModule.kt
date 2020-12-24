package com.app.forecast.di

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    fun providesResources(context: Context): Resources = context.resources
}
