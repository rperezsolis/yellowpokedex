package com.rafaelperez.yellowpokedex.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.rafaelperez.yellowpokedex.R

class MainActivity : AppCompatActivity() {

    private val _navController by lazy { findNavController(R.id.nav_host_fragment_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val args: MainActivityArgs by navArgs()
        val bundle = Bundle()
        bundle.putBoolean("logged", args.logged)
        _navController.setGraph(R.navigation.main_navigation, bundle)
    }
}
