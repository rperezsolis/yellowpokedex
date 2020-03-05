package com.rafaelperez.yellowpokedex.network.service

import com.rafaelperez.yellowpokedex.network.data_transfer_objects.NetworkPokemonContainer
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET(value = "pokemon/")
    fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Deferred<NetworkPokemonContainer>
}
