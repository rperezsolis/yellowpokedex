package com.rafaelperez.yellowpokedex.network.data_transfer_objects

import com.rafaelperez.yellowpokedex.database.entities.PokemonEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkPokemonContainer (
    val count : Int,
    val next : String?,
    val previous : String?,
    val results : List<PokemonDTO>
)

@JsonClass(generateAdapter = true)
data class PokemonDTO(
    val name: String,
    val url: String
)


