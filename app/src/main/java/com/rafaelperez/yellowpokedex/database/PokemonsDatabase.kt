package com.rafaelperez.yellowpokedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rafaelperez.yellowpokedex.database.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonsDatabase : RoomDatabase() {
    abstract val pokemonDao: PokemonDao
}

private lateinit var INSTANCE: PokemonsDatabase

fun getDatabase(context: Context): PokemonsDatabase {
    synchronized(PokemonsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                PokemonsDatabase::class.java,
                "pokemons"
            ).build()
        }
    }
    return  INSTANCE
}