package by.aithusa.pokemonlist.network

import retrofit2.http.GET
import retrofit2.http.Path

data class PokemonResponse(
    val name: String,
    val sprites: Sprites,
    val types: List<PokemonType>,
    val abilities: List<PokemonAbility>
)

data class Sprites(val front_default: String?)

data class PokemonType(val type: Type)
data class Type(val name: String)

data class PokemonAbility(val ability: Ability)
data class Ability(val name: String)

// Retrofit Service Interface
interface PokeApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): PokemonResponse

    @GET("pokemon?limit=100")
    suspend fun getPokemonList(): PokemonListResponse
}

data class PokemonListResponse(val results: List<PokemonListResult>)
data class PokemonListResult(val name: String)
