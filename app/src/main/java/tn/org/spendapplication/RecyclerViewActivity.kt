package tn.org.spendapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

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
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val pokemonAdapter = PokemonAdapter(pokemonList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = pokemonAdapter
    }
}