package com.rafaelperez.yellowpokedex.network.service

import com.rafaelperez.yellowpokedex.network.data_transfer_objects.PokemonDetailDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonDetailService {
    @GET(value = "{url}")
    fun getPokemonDetail(@Path("url") url: String): Deferred<PokemonDetailDTO>
}