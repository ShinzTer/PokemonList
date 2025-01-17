package by.aithusa.pokemonlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import by.aithusa.pokemonlist.database.PokemonEntity
import by.aithusa.pokemonlist.databinding.PokemonCardViewBinding

class PokemonAdapter(private val onClick: (PokemonEntity) -> Unit) :
    ListAdapter<PokemonEntity, PokemonViewHolder>(PokemonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding =
            PokemonCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}