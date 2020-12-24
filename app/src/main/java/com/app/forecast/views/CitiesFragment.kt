package com.app.forecast.views


import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.forecast.R
import com.app.forecast.databinding.FragmentCitiesBinding
import com.app.forecast.di.components.CitiesComponent
import com.app.forecast.utils.Status
import com.app.forecast.viewmodels.CitiesViewModel
import javax.inject.Inject

class CitiesFragment: Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: FragmentCitiesBinding
    private lateinit var citiesComponent: CitiesComponent

    @Inject
    lateinit var citiesViewModel: CitiesViewModel

    @Inject
    lateinit var citiesAdapter: CitiesAdapter

    override fun onAttach(context: Context) {
        citiesComponent = (activity as MainActivity).mainComponent.citiesComponent().create()
        citiesComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_cities, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.recyclerContainer.setOnRefreshListener(this)
        binding.citiesRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = citiesAdapter.apply {
                onItemClickListener.fragment = this@CitiesFragment
            }

            setFirstItemTopMargin()
        }

        setupObserver()
    }

    private fun setupObserver() {
        citiesViewModel.getCities().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.recyclerContainer.isRefreshing = false
                    citiesAdapter.data = it.data
                }
                Status.LOADING -> {
                    binding.recyclerContainer.isRefreshing = true
                }
                Status.ERROR -> {
                    binding.recyclerContainer.isRefreshing = false
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onRefresh() {
        citiesViewModel.refresh()
    }
}

private fun RecyclerView.setFirstItemTopMargin() {
    val marginTop = resources.getDimension(R.dimen.recyclerview_item_margin).toInt()
    addItemDecoration(
        object: RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                if (parent.getChildLayoutPosition(view) == 0) {
                    outRect.top = marginTop
                } else {
                    outRect.top = 0
                }
            }
        }
    )
}
