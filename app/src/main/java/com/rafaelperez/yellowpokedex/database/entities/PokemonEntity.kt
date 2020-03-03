package com.rafaelperez.yellowpokedex.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rafaelperez.yellowpokedex.domain.Pokemon

@Entity
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val thumbnailUrl: String
)

fun List<PokemonEntity>.asDomainModel(): List<Pokemon> {
    return map {
        Pokemon(
            number = it.id,
            name = it.name,
            thumbnailUrl = it.thumbnailUrl
        )
    }
}



