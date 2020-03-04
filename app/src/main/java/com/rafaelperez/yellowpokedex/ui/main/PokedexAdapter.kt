package com.rafaelperez.yellowpokedex.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rafaelperez.yellowpokedex.R
import com.rafaelperez.yellowpokedex.databinding.PokemonFeedItemBinding
import com.rafaelperez.yellowpokedex.domain.Pokemon

class PokedexAdapter : PagedListAdapter<Pokemon, RecyclerView.ViewHolder>(pokemonComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        val dataBinding: PokemonFeedItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PokedexViewHolder.LAYOUT,
            parent,
            false
        )
        return PokedexViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PokedexViewHolder).viewDataBinding.also {
            it.pokemon = getItem(position)
        }
    }

    companion object {
        private val pokemonComparator = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.number == newItem.number

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }
}

class PokedexViewHolder(val viewDataBinding: PokemonFeedItemBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.pokemon_feed_item
    }
}