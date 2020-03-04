package com.rafaelperez.yellowpokedex.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rafaelperez.yellowpokedex.database.entities.PokemonEntity

@Dao
interface PokemonDao {

    @Query("select * from pokemonentity")
    fun getPokemons(): DataSource.Factory<Int, PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pokemons: PokemonEntity)
}