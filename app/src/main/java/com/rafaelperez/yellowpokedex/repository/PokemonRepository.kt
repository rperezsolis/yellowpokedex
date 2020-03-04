package com.rafaelperez.yellowpokedex.repository

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import com.rafaelperez.yellowpokedex.database.PokemonsDatabase
import com.rafaelperez.yellowpokedex.database.entities.asDomainModel
import com.rafaelperez.yellowpokedex.domain.Pokemon
import com.rafaelperez.yellowpokedex.network.data_transfer_objects.asDatabaseModel
import com.rafaelperez.yellowpokedex.network.service.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class PokemonRepository(private  val database: PokemonsDatabase) {

    private val dataSourceFactory: DataSource.Factory<Int, Pokemon> = database.pokemonDao.getPokemons().map() {
        it.asDomainModel()
    }

    val pokemons = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)

    suspend fun refreshPokemons() {
        withContext(Dispatchers.IO) {
            val pokemons = Network.pokemonServiceImpl.getPokemons().await()
            val pokemonsDetails = pokemons.results.map {
                return@map Network.pokemonDetailServiceImpl.getPokemonDetail(it.url.substring(26))
            }.awaitAll()

            database.pokemonDao.insertAll(*pokemonsDetails.asDatabaseModel())
        }
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}