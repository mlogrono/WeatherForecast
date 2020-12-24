package com.app.forecast.di

import com.app.forecast.repo.Repository
import com.app.forecast.repo.RepositoryImpl
import com.app.forecast.views.CitiesAdapter
import com.app.forecast.views.OnItemClickListenerImpl
import dagger.Module
import dagger.Provides

//@Module
//abstract class CitiesModule {
//    @Binds
//    abstract fun providesRepository(repo: RepositoryImpl): Repository
//
//    @Binds
//    abstract fun providesItemClicKListener(listener: OnItemClickListenerImpl): CitiesAdapter.OnItemClickListener
//
//}

@Module
class CitiesModule {
    @Provides
    fun providesRepository(repo: RepositoryImpl): Repository {
        return repo
    }

    @Provides
    fun providesItemClicKListener(listener: OnItemClickListenerImpl): CitiesAdapter.OnItemClickListener {
        return listener
    }

}