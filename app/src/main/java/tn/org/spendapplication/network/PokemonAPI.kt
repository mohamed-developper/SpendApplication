package tn.org.spendapplication.network

import retrofit2.Call
import retrofit2.http.GET

interface PokemonAPI {

    @GET("scitbiz/0bfdd96d3ab9ee20c2e572e47c6834c7/raw/pokemons.json")
    fun getAllPokemons(): Call<PokemonModel>

}