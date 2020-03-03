package com.rafaelperez.yellowpokedex.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.rafaelperez.yellowpokedex.R
import com.rafaelperez.yellowpokedex.viewmodels.MainViewModel
import com.rafaelperez.yellowpokedex.viewmodels.PokedexViewModel

class PokedexFragment : Fragment() {

    private lateinit var viewModel: PokedexViewModel

    private lateinit var sharedViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sharedViewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        if(!sharedViewModel.logged.value!!) {
            goToLogin()
        }

        sharedViewModel.logged.observe(viewLifecycleOwner, Observer {
            if (!it) {
                saveLoggedStatus(it)
                goToLogin()
            }
        })

        viewModel = ViewModelProvider(this).get(PokedexViewModel::class.java)

        return inflater.inflate(R.layout.fragment_pokedex, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                sharedViewModel.logout()
                super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToLogin() {
        findNavController().navigate(PokedexFragmentDirections.actionPokedexFragmentToLoginFragment())
    }

    private fun saveLoggedStatus(logged: Boolean) {
        val sharedPref = activity?.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putBoolean(getString(R.string.logged_status_key), logged)
            apply()
        }
    }
}
