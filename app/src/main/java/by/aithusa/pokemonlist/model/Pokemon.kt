package by.aithusa.pokemonlist.model

import androidx.annotation.DrawableRes

data class Pokemon(
    val id: Int,
    val name: String,
    @DrawableRes val imageRes: Int,
    val type: List<String>,
    val weight: Int,
    val height: Int
)