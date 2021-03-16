package tn.org.spendapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class PokemonAdapter(private val list: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount() = list.size

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var title: TextView = view.findViewById(R.id.title)
        private var description: TextView = view.findViewById(R.id.description)
        private var image: ImageView = view.findViewById(R.id.image)

        fun bind(pokemon: Pokemon) {
            title.text = pokemon.name
            description.text = pokemon.xdescription
            Glide.with(image)
                .load(pokemon.imageurl)
                .placeholder(R.drawable.ic_cloud_download)
                .error(R.drawable.ic_error_outline)
                .circleCrop()
                .into(image)
        }
    }
}