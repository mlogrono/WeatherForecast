package com.app.forecast.views

import android.graphics.drawable.Drawable
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.app.forecast.BR
import com.app.forecast.utils.Temperature
import kotlinx.parcelize.Parcelize


/**
 * Future improvement. Not yet as useful as wanted since recyclerview is
 * recreated anyway when pressing back from WeatherDetailsFragment
 * It might be better to find a way to refresh the data individually
 * rather than recreating/repopulating the entire recyclerview
 */
@Parcelize
class BoundDisplayItem: BaseObservable(), Parcelable {
    @get:Bindable
    var cityId: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.cityId)
        }

    @get:Bindable
    var currentTemp: Float = 0f
        set(value) {
            field = value
            notifyPropertyChanged(BR.currentTemp)
            color = this.getTemperatureColor()
        }

    @get:Bindable
    var cityName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.cityName)
        }

    @get:Bindable
    var indicator: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.indicator)
        }

    @get:Bindable
    var favorite: Drawable? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.favorite)
        }

    @get:Bindable
    var skies: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.skies)
        }

    @get:Bindable
    var color: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.color)
        }
}


fun BoundDisplayItem.getTemperatureColor(): Int {
    return when (currentTemp) {
        in Temperature.FREEZING -> Temperature.FREEZING.tint
        in Temperature.COLD -> Temperature.COLD.tint
        in Temperature.WARM -> Temperature.WARM.tint
        else -> Temperature.HOT.tint
    }
}