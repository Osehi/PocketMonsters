package com.polish.pocketmonsters.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.polish.pocketmonsters.databinding.PokemonListItemBinding
import com.polish.pocketmonsters.networkdatamodel.Result
import com.polish.pocketmonsters.utils.GlideApp
import com.polish.pocketmonsters.utils.getUrl

class PokemonAdapter(private val pokemonIcon:List<Result>):RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    /**
     * view holder class
     */
    class PokemonViewHolder(private val binding: PokemonListItemBinding):RecyclerView.ViewHolder(binding.root){
        /**
         * get reference to the views on the viewHolder
         */
        val pokemonName = binding.fragDisplayPokemonCharacterNameTv
        val pokemonImage = binding.fragDisplayPokemonCharacterImageIv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val pokemonListBinding = PokemonListItemBinding.inflate(layoutInflater, parent, false)
        return PokemonViewHolder(pokemonListBinding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        /**
         * item is the variable that holds a single data, so data is set to the view on the viewHolder
         */
        val item = pokemonIcon[position]
        holder.pokemonName.text = item.name.toString()
        val urlFromServer = item.url.toString()
        val number = getUrl(urlFromServer)
        val pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
        Log.d("onBindView", "the result:$pokemonImageUrl")
        Glide.with(holder.itemView.context).load(pokemonImageUrl).into(holder.pokemonImage)
    }

    override fun getItemCount(): Int {
        return pokemonIcon.size
    }
}