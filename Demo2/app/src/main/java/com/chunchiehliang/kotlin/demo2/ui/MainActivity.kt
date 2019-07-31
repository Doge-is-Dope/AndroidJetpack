package com.chunchiehliang.kotlin.demo2.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.chunchiehliang.kotlin.demo2.R
import com.chunchiehliang.kotlin.demo2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        binding.fabFilter.setOnClickListener {

            navController.navigate(R.id.testFragment)
        }

        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(AppBarConfiguration(navController.graph)) || super.onSupportNavigateUp()
    }

    private fun setupNavigation() {
        setSupportActionBar(binding.toolbar)

        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            when (destination.id) {
                R.id.movieFragment -> {
//                    binding.bottomAppBar.visibility = View.VISIBLE
                    binding.fabFilter.apply {
                        show()
                    }
                }
                else -> {
//                    binding.bottomAppBar.visibility = View.GONE
                    binding.fabFilter.apply {
                        hide()
                    }
                }
            }
        }
    }

}
