package by.aithusa.pokemonlist

import androidx.recyclerview.widget.DiffUtil
import by.aithusa.pokemonlist.database.PokemonEntity

class PokemonDiffCallback : DiffUtil.ItemCallback<PokemonEntity>() {
    override fun areItemsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
        return oldItem == newItem
    }
}