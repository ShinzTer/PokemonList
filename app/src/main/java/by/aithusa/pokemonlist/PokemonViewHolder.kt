package by.aithusa.pokemonlist

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.aithusa.pokemonlist.databinding.PokemonCardViewBinding
import by.aithusa.pokemonlist.model.Pokemon

class PokemonViewHolder(binding: PokemonCardViewBinding) : RecyclerView.ViewHolder(binding.root) {
    private val pokemonImage: ImageView = binding.pokemonImage
    private val pokemonName: TextView = binding.pokemonName

    fun pokemonCardViewHolder(currentPokemon: Pokemon) {
        pokemonName.text = currentPokemon.name
        pokemonImage.setImageResource(currentPokemon.imageRes)
        itemView.setOnClickListener {
            val context = itemView.context
            val intent = Intent(context, PokemonInfoActivity::class.java)
            intent.putExtra("pokemon_id", currentPokemon.id)
            context.startActivity(intent)
        }
    }
}