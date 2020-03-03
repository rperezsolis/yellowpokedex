package com.rafaelperez.yellowpokedex.network.service

import com.rafaelperez.yellowpokedex.network.data_transfer_objects.NetworkPokemonContainer
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface PokemonService {
    @GET(value = "pokemon/")
    fun getPokemons(): Deferred<NetworkPokemonContainer>
}
