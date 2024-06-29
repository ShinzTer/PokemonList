package by.aithusa.pokemonlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.aithusa.pokemonlist.model.Pokemon

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private var pokemonList = emptyList<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.pokemon_card_view, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentPokemon = pokemonList[position]
        holder.pokemonName.text = currentPokemon.name
        holder.pokemonImage.setImageResource(currentPokemon.imageRes)
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, PokemonInfoActivity::class.java)
            intent.putExtra("pokemon_id", currentPokemon.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = pokemonList.size

    fun updatePokemonList(newItems: List<Pokemon>) {
        val diffUtilCallback = PokemonDiffCallback(pokemonList, newItems)
        pokemonList = newItems
        DiffUtil.calculateDiff(diffUtilCallback).dispatchUpdatesTo(this)
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemonImage: ImageView = itemView.findViewById(R.id.pokemon_image)
        val pokemonName: TextView = itemView.findViewById(R.id.pokemon_name)
    }
}