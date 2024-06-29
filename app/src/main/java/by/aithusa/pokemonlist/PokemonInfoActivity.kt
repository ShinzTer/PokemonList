package by.aithusa.pokemonlist

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import by.aithusa.pokemonlist.databinding.ActivityPokemonInfoBinding
import by.aithusa.pokemonlist.model.Pokemon
import by.aithusa.pokemonlist.repository.PokemonRepository

class PokemonInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonId = intent.getIntExtra("pokemon_id", -1)

        val pokemon: Pokemon? = PokemonRepository.getPokemonById(pokemonId)

        with(binding) {
            pokemon?.let {
                pokemonImage.setImageResource(it.imageRes)
                pokemonName.text = it.name
                "Type:\n${it.type.joinToString(", ")}".also { pokemonType.text = it }
                "${it.weight}kg".also { pokemonWeight.text = it }
                "${it.height}cm".also { pokemonHeight.text = it }
            } ?: run {
                pokemonImage.setImageResource(R.drawable.ic_launcher_foreground)
                pokemonName.text = "Unknown"
                pokemonType.text = "Unknown"
                pokemonWeight.text = "N/A"
                pokemonHeight.text = "N/A"
                }
            }

        val backButton: Button = binding.buttonBack
        backButton.setOnClickListener {
            finish()
        }
    }
}