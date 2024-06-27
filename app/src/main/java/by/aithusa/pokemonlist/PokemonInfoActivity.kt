package by.aithusa.pokemonlist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import by.aithusa.pokemonlist.model.Pokemon
import by.aithusa.pokemonlist.repository.PokemonRepository

class PokemonInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_info)

        val pokemonImage: ImageView = findViewById(R.id.pokemon_image)
        val pokemonName: TextView = findViewById(R.id.pokemon_name)
        val pokemonType: TextView = findViewById(R.id.pokemon_type)
        val pokemonWeight: TextView = findViewById(R.id.pokemon_weight)
        val pokemonHeight: TextView = findViewById(R.id.pokemon_height)

        val pokemonId = intent.getIntExtra("pokemon_id", -1)

        val pokemon: Pokemon? = PokemonRepository.getPokemonById(pokemonId)

        pokemon?.let {
            pokemonImage.setImageResource(it.imageRes)
            pokemonName.text = it.name
            "${it.weight}kg".also { pokemonWeight.text = it }
            "${it.height}cm".also { pokemonHeight.text = it }
            "Type:\n${it.type.joinToString(", ")}".also {pokemonType.text = it}
        } ?: run {
            pokemonImage.setImageResource(R.drawable.ic_launcher_foreground)
            pokemonName.text = "Unknown"
            pokemonType.text = "Unknown"
            pokemonWeight.text = "N/A"
            pokemonHeight.text = "N/A"
        }

        val backButton: Button = findViewById(R.id.button_back)
        backButton.setOnClickListener {
            finish()
        }
    }
}