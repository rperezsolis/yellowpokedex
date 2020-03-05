package com.rafaelperez.yellowpokedex.network.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rafaelperez.yellowpokedex.network.data_transfer_objects.PokemonDetailDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

object Network {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val pokemonServiceImpl = retrofit.create(PokemonService::class.java)

    val pokemonDetailServiceImpl = retrofit.create(PokemonDetailService::class.java)
}