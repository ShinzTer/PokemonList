package by.aithusa.pokemonlist

import android.content.Intent
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
            intent.putExtra("pokemon", pokemon)
            startActivity(intent)
        }

        binding.buttonToTeam.setOnClickListener {
            val intent = Intent(this, PokemonTeamActivity::class.java)
            startActivity(intent)
        }

        binding.buttonRefresh?.setOnClickListener {
            refreshPokemonList()
        }

        binding.recyclerView.adapter = adapter

        loadPokemon()
    }

    private fun refreshPokemonList() {
        lifecycleScope.launch {
            try {
                // Очистка данных
                repository.clearData()
                // Здесь вы можете заново загрузить данные из API или другой источник
                Toast.makeText(this@MainActivity, "Список обновлён!", Toast.LENGTH_SHORT).show()
                // Обновление списка на экране
                loadPokemon()
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Ошибка обновления списка", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadPokemon() {
        lifecycleScope.launch {
            try {
                val pokemonList = repository.getPokemonList()
                adapter.submitList(pokemonList)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Failed to load Pokemon", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}