package com.rafaelperez.yellowpokedex.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.rafaelperez.yellowpokedex.database.getDatabase
import com.rafaelperez.yellowpokedex.domain.Pokemon
import com.rafaelperez.yellowpokedex.repository.PokemonBoundaryCallback
import com.rafaelperez.yellowpokedex.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class PokedexViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val repository = PokemonRepository(database)

    val pokemons: LiveData<PagedList<Pokemon>> = repository.pokemons.build()

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PokedexViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PokedexViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
