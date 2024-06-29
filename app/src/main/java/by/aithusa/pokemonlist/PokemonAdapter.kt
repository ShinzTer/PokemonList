package by.aithusa.pokemonlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.aithusa.pokemonlist.databinding.PokemonCardViewBinding
import by.aithusa.pokemonlist.model.Pokemon

class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>() {

    private var pokemonList = emptyList<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding =
            PokemonCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.pokemonCardViewHolder(pokemonList[position])
    }

    override fun getItemCount() = pokemonList.size

    fun updatePokemonList(newItems: List<Pokemon>) {
        val diffUtilCallback = PokemonDiffCallback(pokemonList, newItems)
        pokemonList = newItems
        DiffUtil.calculateDiff(diffUtilCallback).dispatchUpdatesTo(this)
    }
}