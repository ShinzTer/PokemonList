package by.aithusa.pokemonlist

import androidx.recyclerview.widget.RecyclerView
import by.aithusa.pokemonlist.database.PokemonEntity
import by.aithusa.pokemonlist.databinding.PokemonCardViewBinding
import coil.load

class PokemonViewHolder(
    private val binding: PokemonCardViewBinding,
    private val onClick: (PokemonEntity) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var currentPokemon: PokemonEntity? = null

    init {
        binding.root.setOnClickListener {
            currentPokemon?.let { onClick(it) }
        }
    }

    fun bind(pokemon: PokemonEntity) {
        currentPokemon = pokemon
        binding.pokemonName.text = pokemon.name
        binding.pokemonImage.load(pokemon.imageUrl)
    }
}
