package com.app.forecast.views


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.app.forecast.R
import com.app.forecast.databinding.FragmentDetailsBinding
import com.app.forecast.di.components.WeatherDetailsComponent
import com.app.forecast.models.CityModel
import com.app.forecast.utils.Status
import com.app.forecast.viewmodels.WeatherDetailsViewModel
import javax.inject.Inject

class WeatherDetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val args: WeatherDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var detailsViewModel: WeatherDetailsViewModel

    @Inject
    lateinit var detailsComponent: WeatherDetailsComponent

    override fun onAttach(context: Context) {
        detailsComponent = (activity as MainActivity).mainComponent.weatherDetailsComponent().create()
        detailsComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickFavorite(args.boundItem!!)
        setupObserver()
        detailsViewModel.args = args
    }

    private fun setupObserver() {
        detailsViewModel.getCityWeather().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { model -> updateFragmentUI(model) }
                    binding.infoContainer.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.infoContainer.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateFragmentUI(city: CityModel) {
        binding.content = args.boundItem!!.apply {
            skies = city.weather[0].skies
            currentTemp = (city.temperature.current * 10).toInt() / 10f
        }
        binding.temps = city.temperature

    }

    private fun setOnClickFavorite(boundItem: BoundDisplayItem) {
        binding.favoriteCity.setImageResource(
            if (boundItem.indicator) {
                R.drawable.heart_filled
            } else {
                R.drawable.heart_outlined
            }
        )

        binding.favoriteCity.setOnClickListener {
            if (boundItem.indicator) {
                boundItem.favorite = null
                binding.favoriteCity.setImageResource(R.drawable.heart_outlined)
                boundItem.indicator = false
            } else {
                boundItem.favorite = ResourcesCompat.getDrawable(resources, R.drawable.heart_filled, this.activity?.theme)
                binding.favoriteCity.setImageResource(R.drawable.heart_filled)
                boundItem.indicator = true
            }
        }
    }
}