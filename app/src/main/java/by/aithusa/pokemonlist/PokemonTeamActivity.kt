package by.aithusa.pokemonlist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.aithusa.pokemonlist.TeamManager.removePokemon
import by.aithusa.pokemonlist.database.PokemonEntity
import by.aithusa.pokemonlist.databinding.ActivityPokemonTeamBinding

class PokemonTeamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonTeamBinding
    private lateinit var adapter: PokemonAdapter
    private val team = mutableListOf<PokemonEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PokemonAdapter { pokemon ->
            removePokemonFromTeam(pokemon)
        }

        binding.teamRecyclerView.adapter = adapter

        loadTeam()
    }

    private fun loadTeam() {
        TeamManager.loadTeam(this) // Загружаем команду
        team.addAll(TeamManager.getTeam())
        adapter.submitList(team.toList()) // Обновляем UI
    }

    private fun removePokemonFromTeam(pokemon: PokemonEntity) {
        removePokemon(this, pokemon) // Сохраняем обновленную команду
        adapter.submitList(TeamManager.getTeam().toList()) // Обновляем адаптер
        Toast.makeText(this, "${pokemon.name} удалён из команды", Toast.LENGTH_SHORT).show()
    }
}
