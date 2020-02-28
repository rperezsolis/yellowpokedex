package com.rafaelperez.yellowpokedex.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.rafaelperez.yellowpokedex.R

/**
 * A simple [Fragment] subclass.
 */
class PokedexFragment : Fragment() {

    private val args by navArgs<PokedexFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokedex, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(!args.logged) {
            findNavController().navigate(PokedexFragmentDirections.actionPokedexFragmentToLoginFragment())
        } else {
            Toast.makeText(context, "${args.logged} from main view", Toast.LENGTH_LONG).show()
        }
    }
}
