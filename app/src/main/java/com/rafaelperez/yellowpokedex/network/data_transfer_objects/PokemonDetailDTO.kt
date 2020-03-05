package com.rafaelperez.yellowpokedex.network.data_transfer_objects

import com.rafaelperez.yellowpokedex.database.entities.PokemonEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDetailDTO(
    val id: Int,
    val name: String,
    val sprites: Sprites
)

@JsonClass(generateAdapter = true)
data class Sprites (
    val back_default : String?,
    val back_female : String?,
    val back_shiny : String?,
    val back_shiny_female : String?,
    val front_default : String?,
    val front_female : String?,
    val front_shiny : String?,
    val front_shiny_female : String?
)

fun List<PokemonDetailDTO>.asDatabaseModel(): Array<PokemonEntity> {
    return map {
        PokemonEntity(
            id = it.id,
            name = it.name,
            thumbnailUrl = it.sprites.front_default ?: ""
        )
    }.toTypedArray()
}