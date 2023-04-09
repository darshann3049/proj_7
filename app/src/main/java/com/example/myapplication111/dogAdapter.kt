package com.example.myapplication111

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class dogAdapter(private val dogList: List<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageContain : ImageView
        init {
            // Find our RecyclerView item's ImageView for future use
            imageContain = view.findViewById(R.id.ivDog)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dog_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        Glide.with(holder.itemView)
            .load(dogList[position])
            .centerCrop()
            .into(viewHolder.imageContain)



        holder.imageContain.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Doggo at position $position clicked",
                Toast.LENGTH_SHORT
            ).show()

        }
    }
}
