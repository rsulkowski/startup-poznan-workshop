package com.tomtom.example.areanotifier

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.tomtom.online.sdk.map.MapFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mapFragment: MapFragment
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavController()
        initMainViewModel()
        initMap()
    }

    private fun initNavController() {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initMainViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.mapAction().observe(this, Observer { action ->
            mapFragment.getAsyncMap { tomtomMap -> action.invoke(tomtomMap) }
        })
    }

    private fun initMap() {
        mapFragment = map_fragment as MapFragment
        // TODO: Configure map and add report fragment
    }
}
