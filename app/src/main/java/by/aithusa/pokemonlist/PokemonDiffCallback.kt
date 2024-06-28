package by.aithusa.pokemonlist

import androidx.recyclerview.widget.DiffUtil
import by.aithusa.pokemonlist.model.Pokemon

class PokemonDiffCallback (
    private val oldList: List<Pokemon>,
    private val newList: List<Pokemon>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }