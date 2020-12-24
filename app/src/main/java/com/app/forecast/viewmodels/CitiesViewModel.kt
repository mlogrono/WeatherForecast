package com.app.forecast.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.forecast.models.CityModel
import com.app.forecast.repo.Repository
import com.app.forecast.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CitiesViewModel  @Inject constructor(
    private val repository: Repository?
) : ViewModel() {

    /*
     * Hardcoded parameters.
     * Would have used Resources but had difficulties injecting Resources during init()
     */
    private var cityIds = "1701668,3067696,1835848".removeDuplicates()
    private var units = "metric"
    private var key = "981d9f8a2c5c139aa40cb30e4f4794ec"

    private var citiesLiveData = MutableLiveData<Resource<List<CityModel>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        refresh()
    }

    fun refresh() {
        fetchCities()
    }

    private fun fetchCities() {

        citiesLiveData.postValue(Resource.loading(null))
        compositeDisposable.add(repository!!.getCities(cityIds, units, key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                citiesLiveData.postValue(Resource.success(response.cities))
            }, {
                it.printStackTrace()
                citiesLiveData.postValue(
                    Resource.error(
                        it.message ?: "Something went wrong!",
                        null
                    )
                )
            }))
    }

    fun getCities(): LiveData<Resource<List<CityModel>>> {
        return citiesLiveData
    }
}

private fun String.removeDuplicates() = split(",").distinct().joinToString(",")
