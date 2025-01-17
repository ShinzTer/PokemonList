package by.aithusa.pokemonlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.aithusa.pokemonlist.database.PokemonEntity
import by.aithusa.pokemonlist.databinding.ActivityPokemonInfoBinding
import by.aithusa.pokemonlist.repository.PokemonRepository
import coil.load
import kotlinx.coroutines.launch

class PokemonInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonInfoBinding
    private lateinit var repository: PokemonRepository
    private lateinit var currentPokemon: PokemonEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = PokemonRepository(this)

        val pokemonName = intent.getStringExtra(EXTRA_POKEMON_NAME) ?: return
        loadPokemonInfo(pokemonName)

        currentPokemon = intent.getParcelableExtra("pokemon")
            ?: throw IllegalArgumentException("Pokemon data is missing")

        binding.buttonAddToTeam.setOnClickListener {
            val added = TeamManager.addPokemon(this, currentPokemon)
            if (added) {
                Toast.makeText(
                    this,
                    "${currentPokemon.name} добавлен в команду!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Команда уже заполнена или покемон уже в команде.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        val backButton: Button = binding.buttonBack
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun loadPokemonInfo(name: String) {
        lifecycleScope.launch {
            try {
                val pokemon = repository.getPokemonList().find { it.name == name } ?: return@launch
                with(binding) {
                    pokemonName.text = pokemon.name
                    pokemonImage.load(pokemon.imageUrl)
                    "Type:\n${
                        pokemon.types.replace(
                            ",",
                            ", "
                        )
                    }".also { pokemonTypes.text = it }
                    "Abilities:\n${
                        pokemon.abilities.replace(
                            ",",
                            ", "
                        )
                    }".also { pokemonAbilities.text = it }
                }
            } catch (e: Exception) {
                with(binding) {
                    pokemonName.text = getString(R.string.unknown)
                    pokemonImage.setImageResource(R.drawable.snorlax)
                    pokemonTypes.text = getString(R.string.unknown)
                    pokemonAbilities.text = getString(R.string.unknown)
                }
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
