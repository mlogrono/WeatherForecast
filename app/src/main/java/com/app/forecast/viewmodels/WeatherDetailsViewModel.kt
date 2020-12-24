package com.app.forecast.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.forecast.models.CityModel
import com.app.forecast.repo.Repository
import com.app.forecast.utils.Resource
import com.app.forecast.views.WeatherDetailsFragmentArgs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherDetailsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    /*
     * Hardcoded parameters.
     * Would have used Resources but had difficulties injecting Resources during init()
     */
    private var units = "metric"
    private var key = "981d9f8a2c5c139aa40cb30e4f4794ec"

    /*
     * To bypass injection
     */
    var args: WeatherDetailsFragmentArgs? = null
        set(newArgs) {
            field = newArgs
            fetchCityWeather()
        }
    private var weatherLiveData = MutableLiveData<Resource<CityModel>>()
    private val compositeDisposable = CompositeDisposable()


    private fun fetchCityWeather() {
        weatherLiveData.postValue(Resource.loading(null))
        compositeDisposable.add(repository.getCityWeather(args!!.boundItem!!.cityName, units, key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->

                Log.i(">>>>", "TO CONFIRM SINGLE: ${response}")
                weatherLiveData.postValue(Resource.success(response))
            }, {
                it.printStackTrace()
                weatherLiveData.postValue(
                    Resource.error(
                        it.message ?: "Something went wrong!",
                        null
                    )
                )
            }))
    }

    fun getCityWeather(): LiveData<Resource<CityModel>> {
        return weatherLiveData
    }
}
