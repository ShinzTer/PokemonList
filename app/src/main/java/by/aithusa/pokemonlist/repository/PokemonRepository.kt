package by.aithusa.pokemonlist.repository

import android.content.Context
import by.aithusa.pokemonlist.database.AppDatabase
import by.aithusa.pokemonlist.database.PokemonEntity
import by.aithusa.pokemonlist.network.PokeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRepository(context: Context) {

    private val api: PokeApiService
    private val pokemonDao = AppDatabase.getInstance(context).pokemonDao()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(PokeApiService::class.java)
    }

    suspend fun getPokemonList(): List<PokemonEntity> {
        return withContext(Dispatchers.IO) {
            val localData = pokemonDao.getAll()
            if (localData.isNotEmpty()) {
                localData
            } else {
                val apiData = api.getPokemonList().results.map { result ->
                    val details = api.getPokemon(result.name)
                    PokemonEntity(
                        name = details.name.replaceFirstChar { it.uppercaseChar() },
                        imageUrl = details.sprites.front_default ?: "",
                        types = details.types.joinToString(",") { it.type.name },
                        abilities = details.abilities.joinToString(",") { it.ability.name }
                    )
                }
                pokemonDao.insertAll(apiData)
                apiData
            }
        }
    }

    suspend fun clearData() {
        withContext(Dispatchers.IO) {
            pokemonDao.clear()
        }
    }
}
