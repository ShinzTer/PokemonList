package repository

import by.aithusa.pokemonlist.R
import model.Pokemon

object PokemonRepository {
    private val pokemons: HashMap<Int, Pokemon> = HashMap(listOf(
        Pokemon(
            id = 1,
            name = "Flareon",
            imageRes = R.drawable.flareon,
            weight = 25,
            height = 90,
            type = listOf("Fire")
        ),
        Pokemon(
            id = 2,
            name = "Jolteon",
            imageRes = R.drawable.jolteon,
            weight = 25,
            height = 80,
            type = listOf("Electric")
        ),
        Pokemon(
            id = 3,
            name = "Snorlax",
            imageRes = R.drawable.snorlax,
            weight = 460,
            height = 210,
            type = listOf("Normal")
        ),
        Pokemon(
            id = 4,
            name = "Tyranitar",
            imageRes = R.drawable.tyranitar,
            weight = 202,
            height = 200,
            type = listOf("Rock","Dark")
        )
    ).associateBy { it.id })

    fun getPokemons(): HashMap<Int, Pokemon> = pokemons
    fun getPokemonById(id: Int): Pokemon? = pokemons[id]
}