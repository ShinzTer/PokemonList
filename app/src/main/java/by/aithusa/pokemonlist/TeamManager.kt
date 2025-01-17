package by.aithusa.pokemonlist

import android.content.Context
import by.aithusa.pokemonlist.database.PokemonEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TeamManager {
    private const val PREFS_NAME = "pokemon_team"
    private const val TEAM_KEY = "team"
    private val team = mutableListOf<PokemonEntity>()

    fun addPokemon(context: Context, pokemon: PokemonEntity): Boolean {
        if (team.size < 6 && !team.contains(pokemon)) {
            team.add(pokemon)
            saveTeam(context)
            return true
        }
        return false
    }

    fun getTeam(): List<PokemonEntity> = team

    fun loadTeam(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val teamJson = sharedPreferences.getString(TEAM_KEY, null)
        if (teamJson != null) {
            val type = object : TypeToken<List<PokemonEntity>>() {}.type
            team.clear()
            team.addAll(Gson().fromJson(teamJson, type))
        }
    }

    private fun saveTeam(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val teamJson = Gson().toJson(team)
        editor.putString(TEAM_KEY, teamJson)
        editor.apply()
    }

    fun removePokemon(context: Context, pokemon: PokemonEntity) {
        team.remove(pokemon)
        saveTeam(context) // Сохраняем изменения
    }
}
