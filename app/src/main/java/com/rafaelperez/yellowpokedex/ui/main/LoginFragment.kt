package com.rafaelperez.yellowpokedex.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.rafaelperez.yellowpokedex.R
import com.rafaelperez.yellowpokedex.databinding.FragmentLoginBinding
import com.rafaelperez.yellowpokedex.viewmodels.LoginViewModel
import com.rafaelperez.yellowpokedex.viewmodels.MainViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var sharedViewModel: MainViewModel

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        sharedViewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.signingIn.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewModel.setUserCredentials(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
            }
        })

        viewModel.saveLoggedState.observe(viewLifecycleOwner, Observer {
            if (it) {
                saveLoggedStatus(it)
                sharedViewModel.setLoggedValue(it)
            }
        })

        viewModel.goToMainView.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigateUp()
            }
        })

        return binding.root
    }

    private fun saveLoggedStatus(logged: Boolean) {
        val sharedPref = activity?.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putBoolean(getString(R.string.logged_status_key), logged)
            apply()
        }
    }
}
