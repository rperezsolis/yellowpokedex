package com.rafaelperez.yellowpokedex.ui.splash_screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.rafaelperez.yellowpokedex.R
import com.rafaelperez.yellowpokedex.databinding.FragmentSplashBinding
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

        GlobalScope.launch {
            delay(2000L)
            val logged = getLoggedStatus()
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainActivity(logged))
            activity?.finish()
        }

        return binding.root
    }

    private fun getLoggedStatus(): Boolean {
        val sharedPref = activity?.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return false
        return sharedPref.getBoolean(getString(R.string.logged_status_key), false)
    }
}
