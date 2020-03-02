package com.rafaelperez.yellowpokedex.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.rafaelperez.yellowpokedex.R
import com.rafaelperez.yellowpokedex.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val args: MainActivityArgs by navArgs()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.setLoggedValue(args.logged)
    }
}
