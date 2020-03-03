package com.rafaelperez.yellowpokedex.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rafaelperez.yellowpokedex.R
import com.rafaelperez.yellowpokedex.databinding.PokemonFeedItemBinding
import com.rafaelperez.yellowpokedex.domain.Pokemon

class PokedexAdapter() : RecyclerView.Adapter<PokedexViewHolder>() {
    var pokemons: List<Pokemon> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        val dataBinding: PokemonFeedItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PokedexViewHolder.LAYOUT,
            parent,
            false
        )
        return PokedexViewHolder(dataBinding)
    }

    override fun getItemCount(): Int = pokemons.size

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.pokemon = pokemons[position]
        }
    }
}

class PokedexViewHolder(val viewDataBinding: PokemonFeedItemBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.pokemon_feed_item
    }
}