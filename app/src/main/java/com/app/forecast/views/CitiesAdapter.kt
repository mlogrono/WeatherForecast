package com.app.forecast.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.app.forecast.databinding.LayoutCityItemBinding
import com.app.forecast.models.CityModel
import javax.inject.Inject

class CitiesAdapter @Inject constructor(val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<CitiesAdapter.DataViewHolder>() {
    internal var data: List<CityModel>? = null
        set(newData) {

            /**
             * New data is found (swipe refresh was initiated) so recyclerview has to update
             * everything. Else, leave as is since single row data will be updated.
             */
            if (newData !== field) {
                field = newData
                newData?.forEach { city ->
                    val boundItem = cityViews.firstOrNull { it.cityId == city.id }
                    ?: BoundDisplayItem().also { cityViews.add(it) }
                    boundItem.apply {
                        cityId = city.id
                        cityName = city.name
//                        favorite = getImageIndicator(resources)
                        currentTemp = (city.temperature.current * 10).toInt() / 10f
                        skies = city.weather[0].skies
                    }
                }
            }
            notifyDataSetChanged()
        }

    /**
     * The contents of BoundRecyclerItem could be stored in a DB
     */
    private val cityViews: MutableList<BoundDisplayItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = LayoutCityItemBinding.inflate(layoutInflater, parent, false)
        return DataViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(cityViews[position], onItemClickListener)
    }

    interface OnItemClickListener {
        var fragment: Fragment
        fun onItemClick(item: BoundDisplayItem)
    }

    class DataViewHolder(private val binding: LayoutCityItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BoundDisplayItem, onClickListener: OnItemClickListener) {
            binding.item = item
            binding.clickListener = onClickListener
        }
    }
}
