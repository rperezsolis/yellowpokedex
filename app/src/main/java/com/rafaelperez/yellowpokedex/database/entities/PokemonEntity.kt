package com.rafaelperez.yellowpokedex.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rafaelperez.yellowpokedex.domain.Pokemon

@Entity
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val thumbnailUrl: String
)

fun PokemonEntity.asDomainModel(): Pokemon {
    return Pokemon(
            number = id.toString(),
            name = name.capitalize(),
            thumbnailUrl = thumbnailUrl
        )
}



