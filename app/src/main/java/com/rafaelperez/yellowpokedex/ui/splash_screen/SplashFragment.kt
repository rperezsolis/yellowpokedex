package com.rafaelperez.yellowpokedex.ui.splash_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController

import com.rafaelperez.yellowpokedex.R
import com.rafaelperez.yellowpokedex.databinding.FragmentSplashBinding
import com.rafaelperez.yellowpokedex.ui.main.MainActivity
import com.rafaelperez.yellowpokedex.viewmodels.SplashViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private lateinit var viewModel: SplashViewModel

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        binding.lifecycleOwner = this

        //todo: instead of this we have to check from the viewModel if the user is already logged or not
        GlobalScope.launch {
            delay(5000L)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainActivity(viewModel.logged))
            activity?.finish()
        }

        return binding.root
    }
}
