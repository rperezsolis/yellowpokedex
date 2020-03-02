package com.rafaelperez.yellowpokedex.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.rafaelperez.yellowpokedex.R
import com.rafaelperez.yellowpokedex.viewmodels.MainViewModel

/**
 * A simple [Fragment] subclass.
 */
class PokedexFragment : Fragment() {

    private lateinit var sharedViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sharedViewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        if(!sharedViewModel.logged) {
            findNavController().navigate(PokedexFragmentDirections.actionPokedexFragmentToLoginFragment())
        }

        return inflater.inflate(R.layout.fragment_pokedex, container, false)
    }
}
