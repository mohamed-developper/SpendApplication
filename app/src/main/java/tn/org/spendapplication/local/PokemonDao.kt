package tn.org.spendapplication.local

import androidx.room.*
import tn.org.spendapplication.Pokemon

@Dao
interface PokemonDao {

    @Insert
    suspend fun insertPokemon(pokemon: Pokemon)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPokemon(listPokemon: List<Pokemon>)

    @Query("SELECT * FROM pokemon_table")
    suspend fun getAllPokemons(): List<Pokemon>

    @Query("SELECT * FROM pokemon_table WHERE id = :pokemonId")
    suspend fun getPokemonByID(pokemonId: Int): Pokemon

    @Update
    suspend fun updatePokemon(pokemon: Pokemon)

    @Delete
    suspend fun deletePokemon(pokemon: Pokemon)

    @Query("DELETE FROM pokemon_table WHERE id = :pokemonId")
    suspend fun deletePokemonByID(pokemonId: Int)

}