package com.rafaelperez.yellowpokedex.repository

import androidx.paging.PagedList
import com.rafaelperez.yellowpokedex.domain.Pokemon
import kotlinx.coroutines.*

class PokemonBoundaryCallback(private val repository: PokemonRepository) : PagedList.BoundaryCallback<Pokemon>() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onZeroItemsLoaded() {
        refreshPokemons()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Pokemon) {
        refreshPokemons()
    }

    private fun refreshPokemons() {
         viewModelScope.launch {
             repository.refreshPokemons()
         }
    }
}