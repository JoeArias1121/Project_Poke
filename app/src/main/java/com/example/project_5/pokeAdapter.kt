package com.example.project_5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class pokeAdapter (private var pokeList: List<String>, private var nameList: List<String>, private var weightList: List<String>, private var heightList: List<String>): RecyclerView.Adapter<pokeAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pokeImage: ImageView
        val pokeHeight : TextView
        val pokeWeight : TextView
        val pokeName : TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            pokeImage = view.findViewById(R.id.poke_image)
            pokeHeight = view.findViewById(R.id.height)
            pokeWeight = view.findViewById(R.id.weight)
            pokeName = view.findViewById(R.id.pokeName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.poke_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var t = ""
        //updates weight
        t = weightList[position]
        holder.pokeWeight.text = "Weight: $t"
        //updates height
        t = heightList[position]
        holder.pokeHeight.text ="Height: $t"
        //updates name
        holder.pokeName.text = nameList[position]
        //updates image
        Glide.with(holder.itemView)
            .load(pokeList[position])
            .centerCrop()
            .into(holder.pokeImage)
    }

    override fun getItemCount() = pokeList.size

}