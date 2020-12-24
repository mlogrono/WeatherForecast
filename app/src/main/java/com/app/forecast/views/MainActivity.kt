package com.app.forecast.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.app.forecast.App
import com.app.forecast.R
import com.app.forecast.databinding.ActivityMainBinding
import com.app.forecast.di.components.AppComponent
import javax.inject.Inject


class MainActivity @Inject constructor(): AppCompatActivity() {
    lateinit var mainComponent: AppComponent
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainComponent = (application as App).appComponent

        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main) as ActivityMainBinding
        setSupportActionBar(binding.toolbar)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val appBarConfiguration = AppBarConfiguration(navHostFragment.navController.graph)
        NavigationUI.setupWithNavController(
            binding.toolbar,
            navHostFragment.navController,
            appBarConfiguration
        )
    }

}

