package com.rafaelperez.yellowpokedex.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.rafaelperez.yellowpokedex.R
import com.rafaelperez.yellowpokedex.databinding.FragmentPokedexBinding
import com.rafaelperez.yellowpokedex.viewmodels.MainViewModel
import com.rafaelperez.yellowpokedex.viewmodels.PokedexViewModel

class PokedexFragment : Fragment() {

    private lateinit var sharedViewModel: MainViewModel

    private val viewModel: PokedexViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, PokedexViewModel.Factory(activity.application)).get(PokedexViewModel::class.java)
    }

    private lateinit var viewModelAdapter: PokedexAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sharedViewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        val binding: FragmentPokedexBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokedex, container, false)

        if(!sharedViewModel.logged.value!!) {
            goToLogin()
        } else {
            binding.lifecycleOwner = viewLifecycleOwner
            binding.viewModel = viewModel
            viewModelAdapter = PokedexAdapter()
            binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
                layoutManager = LinearLayoutManager(context)
                adapter = viewModelAdapter
            }
        }

        sharedViewModel.logged.observe(viewLifecycleOwner, Observer {
            if (!it) {
                saveLoggedStatus(it)
                goToLogin()
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.pokemons.observe(viewLifecycleOwner, Observer {
            viewModelAdapter.submitList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            if (it!=null && it.isNotEmpty()) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })
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
