package com.app.forecast.views

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import javax.inject.Inject



class OnItemClickListenerImpl @Inject constructor(): CitiesAdapter.OnItemClickListener {
    override lateinit var fragment: Fragment
    override fun onItemClick(item: BoundDisplayItem) {
        val action = CitiesFragmentDirections.actionShowDetails(item)
        findNavController(fragment).navigate(action)
    }
}