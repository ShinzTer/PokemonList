package by.aithusa.pokemonlist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey val name: String,
    val imageUrl: String,
    val types: String,
    val abilities: String
)