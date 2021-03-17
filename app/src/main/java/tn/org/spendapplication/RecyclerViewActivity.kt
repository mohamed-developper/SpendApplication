package tn.org.spendapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.org.spendapplication.local.PokemonDatabase
import tn.org.spendapplication.network.PokemonAPI
import tn.org.spendapplication.network.PokemonModel

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val pokemonList = mutableListOf<Pokemon>()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val pokemonAdapter = PokemonAdapter(pokemonList)

        pokemonAdapter.pokemonClickListener = object : PokemonAdapter.PokemonClickListener {
            override fun onPokemonClicked(pokemon: Pokemon) {
                Snackbar.make(recyclerView, pokemon.name, Snackbar.LENGTH_LONG).show()
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = pokemonAdapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PokemonAPI::class.java).getAllPokemons().enqueue(
            object : Callback<PokemonModel> {
                override fun onResponse(
                    call: Call<PokemonModel>,
                    response: Response<PokemonModel>
                ) {

                    if (response.isSuccessful) {
                        val newList = response.body()!!
                            //.filter { it.attack > 50 }
                            .map {
                                Pokemon(
                                    name = it.name,
                                    xdescription = it.xdescription,
                                    imageurl = it.imageurl
                                )
                            }.sortedBy {
                                it.name
                            }
                        Log.e("TAG", "onResponse: START THREAD")
                        GlobalScope.launch {
                            Log.e("TAG", "onResponse: START INSERTION")
                            PokemonDatabase.getInstance(this@RecyclerViewActivity)
                                .getPokemonDao()
                                .insertAllPokemon(newList)
                            Log.e("TAG", "onResponse: END INSERTION")
                        }
                        Log.e("TAG", "onResponse: END THREAD")
                        pokemonList.addAll(newList)
                        pokemonAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<PokemonModel>, t: Throwable) {
                    GlobalScope.launch(Dispatchers.Main) {
                        val newList = PokemonDatabase.getInstance(this@RecyclerViewActivity)
                            .getPokemonDao()
                            .getAllPokemons()
                        pokemonList.addAll(newList)
                        pokemonAdapter.notifyDataSetChanged()
                    }
                }
            }
        )
        /*
        val pokemonList = listOf(
            Pokemon(
                "Charmander",
                "It has a preference for hot things. When it rains, steam is said to spout from the tip of its tail.",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png"
            ),
            Pokemon(
                "Charmeleon",
                "It has a barbaric nature. In battle, it whips its fiery tail around and slashes away with sharp claws.",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/005.png"
            ),
            Pokemon(
                "Charizard",
                "It spits fire that is hot enough to melt boulders. It may cause forest fires by blowing flames.",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/006.png"
            )
        )
         */


        val touchlistener = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                val newPosition = target.adapterPosition
                val oldPosition = viewHolder.adapterPosition

                pokemonList.add(newPosition, pokemonList[oldPosition])
                pokemonList.removeAt(oldPosition)
                pokemonAdapter.notifyItemMoved(oldPosition, newPosition)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                pokemonList.removeAt(position)
                pokemonAdapter.notifyItemRemoved(position)
            }
        }

        ItemTouchHelper(touchlistener).attachToRecyclerView(recyclerView)
    }
}