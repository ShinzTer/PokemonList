package by.aithusa.pokemonlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.aithusa.pokemonlist.databinding.ActivityPokemonInfoBinding
import by.aithusa.pokemonlist.repository.PokemonRepository
import coil.load
import kotlinx.coroutines.launch

class PokemonInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonInfoBinding
    private lateinit var repository: PokemonRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = PokemonRepository(this)

        val pokemonName = intent.getStringExtra(EXTRA_POKEMON_NAME) ?: return
        loadPokemonInfo(pokemonName)

        val backButton: Button = binding.buttonBack
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun loadPokemonInfo(name: String) {
        lifecycleScope.launch {
            try {
                val pokemon = repository.getPokemonList().find { it.name == name } ?: return@launch
                binding.pokemonName.text = pokemon.name
                binding.pokemonImage.load(pokemon.imageUrl)
                binding.pokemonTypes.text = "Types:\n" + pokemon.types.replace(",", ", ")
                binding.pokemonAbilities.text = "Abilities:\n" + pokemon.abilities.replace(",", ", ")
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    companion object {
        private const val EXTRA_POKEMON_NAME = "pokemon_name"

        fun newIntent(context: Context, pokemonName: String): Intent {
            return Intent(context, PokemonInfoActivity::class.java).apply {
                putExtra(EXTRA_POKEMON_NAME, pokemonName)
            }
        }
    }
}
