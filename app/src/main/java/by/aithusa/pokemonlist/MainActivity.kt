package by.aithusa.pokemonlist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import by.aithusa.pokemonlist.databinding.ActivityMainBinding
import by.aithusa.pokemonlist.repository.PokemonRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: PokemonRepository
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = PokemonRepository(this)

        adapter = PokemonAdapter { pokemon ->
            val intent = PokemonInfoActivity.newIntent(this, pokemon.name)
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter

        loadPokemon()
    }

    private fun loadPokemon() {
        lifecycleScope.launch {
            try {
                val pokemonList = repository.getPokemonList()
                adapter.submitList(pokemonList)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Failed to load Pokemon", Toast.LENGTH_SHORT).show()
            }
        }
    }
}